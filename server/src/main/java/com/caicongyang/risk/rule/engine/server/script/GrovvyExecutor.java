package com.caicongyang.risk.rule.engine.server.script;

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
    public Object execute(String script, ScriptContext ctx) {
        return null;
    }

    @Override
    public void afterFlow(ScriptContext ctx) {

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
     *  exec by javascript
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
