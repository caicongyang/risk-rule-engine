package com.caicongyang.risk.rule.engine.server.script;

import java.lang.reflect.InvocationTargetException;

public interface IScriptExecutor {


    void beforeFlow(ScriptContext ctx);

    Object execute(String script, ScriptContext ctx) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    void afterFlow(ScriptContext ctx);


}
