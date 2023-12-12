package com.caicongyang.risk.rule.engine.server.script;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.codehaus.groovy.runtime.InvokerHelper;

import javax.script.Invocable;
import javax.script.ScriptEngine;
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


    public Object invoke(String scriptId) {

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

        Object result = scriptInstance.invokeMethod("match", new Object[]{map});
        return result;
    }


    private static void invokeWithGroovyClassLoader(String scriptText, String function, Object... objects) throws Exception {
        GroovyClassLoader classLoader = new GroovyClassLoader();
        Class groovyClass = classLoader.parseClass(scriptText);
        try {
            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            groovyObject.invokeMethod(function, objects);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private static <T> T invokeWithScriptEngine(String script, String function, Object... objects) throws Exception {
        ScriptEngine scriptEngine = scriptEngineFactory.getScriptEngine();
        scriptEngine.eval(script);
        return (T) ((Invocable) scriptEngine).invokeFunction(function, objects);
    }


    private static <T> T invokeWithGroovyShell(String scriptText, String function, Object... objects) throws Exception {
        Script script = groovyShell.parse(scriptText);
        return (T) InvokerHelper.invokeMethod(script, function, objects);
    }


}
