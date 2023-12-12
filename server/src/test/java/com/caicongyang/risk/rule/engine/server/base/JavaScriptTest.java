package com.caicongyang.risk.rule.engine.server.base;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavaScriptTest {


    public static void main(String[] args) throws Exception {
        String js = " function add (a, b) { " +
                "         var sum = a + b;  " +
                //js调用java类
                "         java.lang.System.out.println(\"Script sum=\" + sum); " +
                "         return java.lang.Integer.valueOf(sum);  " +
                "}";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        engine.eval(js);
        Invocable jsInvoke = (Invocable) engine;
        Object result = jsInvoke.invokeFunction("add", new Object[]{1, 2});
        System.out.println(result);
    }

}
