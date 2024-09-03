package com.caicongyang.risk.rule.engine.server.script;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.caicongyang.risk.common.RiskFact;
import com.caicongyang.risk.common.RiskResult;
import com.caicongyang.risk.rule.engine.server.domain.RiskRecord;
import com.caicongyang.risk.rule.engine.server.domain.RiskRuleConfig;
import com.caicongyang.risk.rule.engine.server.service.IRiskRecordService;
import com.caicongyang.risk.rule.engine.server.service.IRiskRuleConfigService;
import com.caicongyang.risk.rule.engine.server.utils.JsonUtils;
import com.caicongyang.risk.rule.engine.server.utils.MD5Utils;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * grovvy engine
 */
@Service(value = "grovvyExecutor")
public class GrovvyExecutor implements IScriptExecutor {


    private static final Logger LOGGER = LoggerFactory.getLogger(GrovvyExecutor.class);


    private final ConcurrentHashMap<String, GroovyObject> groovyMap = new ConcurrentHashMap();

    private final ReentrantLock lock = new ReentrantLock();


    private static final GroovyScriptEngineFactory scriptEngineFactory = new GroovyScriptEngineFactory();

    private static GroovyShell groovyShell = new GroovyShell();


    @Autowired
    private ExecutorService executor;


    @Autowired
    private IRiskRuleConfigService riskRuleConfigService;

    @Autowired
    private IRiskRecordService recordService;


    @Override
    public void beforeFlow(RiskFact fact, ScriptContext ctx) {


    }

    @Override
    public RiskResult run(RiskFact fact, ScriptContext ctx) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        beforeFlow(fact, ctx);

        try {
            // 1.执行factor grovvy
            //  多线程执行 factory script 脚本
            ArrayList<Callable<String>> callables = new ArrayList<>();
            Map<String, String> factorMap = ctx.getFactorMap();
            for (String key : factorMap.keySet()) {
                callables.add(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        String grovvyScript = factorMap.get(key);
                        invokeWithGroovyClassLoader(grovvyScript, "run", fact, ctx);
                        return "true";
                    }
                });
            }

            try {
                executor.invokeAll(callables, 3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                LOGGER.error("异步获取因子错误：", e);
            }


            // 2.执行rule grovvy shell， 获取所有需要执行的action rule
            //  根据场景从数据库获取规则

            List<RiskRuleConfig> list = riskRuleConfigService.list(new LambdaQueryWrapper<RiskRuleConfig>().eq(RiskRuleConfig::getCode, ctx.getSceneCode()));
            if (CollectionUtils.isEmpty(list)) {
                return RiskResult.pass("未配置风控规则，自动通过！");
            }
            // 规则因子编码： 因子的的值
            Map<String, Object> factorValueMap = ctx.getFactorValueMap();
            //需要执行的action code
            List<String> actionCodes = ctx.getActionCode();
            for (RiskRuleConfig config : list) {
                String cmd = "return " + factorValueMap.get(config.getFactorCode()) + " " + config.getOperator() + " " + config.getFactorCodeValue();
                Boolean flag = (Boolean) groovyShell.evaluate(cmd);
                //命中该规则，则需要执行该action
                if (flag) {
                    actionCodes.add(config.getActionCode());
                }
                // 是否命中规则则返合
                if (flag && ctx.getFireOne()) {
                    break;
                }
            }

            Map<String, String> actionMap = ctx.getActionMap();
            // 3.执行action;
            for (String actioncode : actionCodes) {
                String actionScript = actionMap.get(actioncode);
                invoke(actionScript, "run", ctx);
                // 每次执行的结果放入到ctx.fireRuleMap 中
            }


            Map<String, RiskResult> fireRuleMap = ctx.getFireRuleMap();
            if (ctx.getFireOne()) {
                Set<String> keySet = fireRuleMap.keySet();

                if (!keySet.isEmpty()) {
                    String firstKey = keySet.iterator().next();
                    return fireRuleMap.get(firstKey);
                } else {
                    return RiskResult.pass();
                }
            } else {
                //  输出多个校验，按优先级高的进行输出进行校验
                Optional<RiskResult> first = fireRuleMap.values().stream().sorted(Comparator.comparing(RiskResult::getCode).reversed()).findFirst();
                return first.get();
            }
        } finally {

        }
    }


    @Override
    public void afterFlow(RiskFact fact, ScriptContext ctx, RiskResult result) {
        // 异步记录请求参数和风控结果到db
        RiskRecord record = new RiskRecord();
        record.setRequestCode(fact.getRequestCode());
        record.setSceneCode(fact.getSceneCode());
        record.setRequestJson(JsonUtils.jsonFromObject(record));
        record.setFireRuleJson(JsonUtils.jsonFromObject(ctx.getFireRuleMap()));
        record.setResultJson(JsonUtils.jsonFromObject(result));
        recordService.save(record);

    }


    public Object invoke(String script, String function, Object... objects) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String scriptId = MD5Utils.md5Hex(script);
        GroovyObject scriptInstance = groovyMap.get(scriptId);
        if (scriptInstance == null) {
            lock.lock();
            try {
                scriptInstance = groovyMap.get(scriptId);
                if (scriptInstance == null) {
                    GroovyClassLoader loader = new GroovyClassLoader();
                    Class scriptClass = loader.parseClass(script);
                    scriptInstance = (GroovyObject) scriptClass.getDeclaredConstructor().newInstance();
                    groovyMap.put(scriptId, scriptInstance);
                }
            } finally {
                lock.unlock();
            }
        }
        Object result = scriptInstance.invokeMethod(function, objects);
        return result;
    }


    private static <T> T invokeWithGroovyClassLoader(String scriptText, String function, Object... objects) {
        GroovyClassLoader classLoader = new GroovyClassLoader();
        Class groovyClass = classLoader.parseClass(scriptText);
        try {
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            return (T) groovyObject.invokeMethod(function, objects);
        } catch (InstantiationException e) {
            throw new RuntimeException("执行grovvy失败：", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("执行grovvy失败：", e);
        }
    }


    /**
     * exec by javascript
     *
     * @param script
     * @param function
     * @param objects
     * @param <T>
     * @return
     * @throws Exception
     */
    private static <T> T invokeWithScriptEngine(String script, String function, Object... objects) throws Exception {
        ScriptEngine scriptEngine = scriptEngineFactory.getScriptEngine();
        scriptEngine.eval(script);
        return (T) ((Invocable) scriptEngine).invokeFunction(function, objects);
    }


    /**
     * exec by grovvy shell
     *
     * @param scriptText
     * @param function
     * @param objects
     * @param <T>
     * @return
     * @throws Exception
     */
    private static <T> T invokeWithGroovyShell(String scriptText, String function, Object... objects) throws Exception {
        Script script = groovyShell.parse(scriptText);
        return (T) InvokerHelper.invokeMethod(script, function, objects);
    }


}
