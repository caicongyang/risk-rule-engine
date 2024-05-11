package com.caicongyang.risk.rule.engine.server.script;

import com.caicongyang.risk.common.RiskResult;
import com.caicongyang.risk.rule.engine.server.domain.RiskRuleConfig;
import com.caicongyang.risk.rule.engine.server.utils.MD5Utils;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.codehaus.groovy.runtime.InvokerHelper;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * grovvy engine
 */
public class GrovvyExecutor implements IScriptExecutor {


    private final ConcurrentHashMap<String, GroovyObject> groovyMap = new ConcurrentHashMap();


    private final ReentrantLock lock = new ReentrantLock();

    private static final GroovyScriptEngineFactory scriptEngineFactory = new GroovyScriptEngineFactory();

    private static GroovyShell groovyShell = new GroovyShell();


    @Override
    public void beforeFlow(ScriptContext ctx) {


    }

    @Override
    public Object execute(String script, ScriptContext ctx) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // 执行factor grovvy

        // todo 多线程执行 factory script 脚本


        // 执行rule grovvy shell
        // 根据场景从数据库获取规则皮遏制
        List<RiskRuleConfig> list = new ArrayList<>();
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
        // 执行action;
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
            // todo 输出多个校验，按优先级高的进行输出进行校验


        }
        return RiskResult.pass();

    }

    @Override
    public void afterFlow(ScriptContext ctx) {
        // 异步记录请求参数和风控结果
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
