package com.caicongyang.risk.rule.engine.server.script;

public interface IScriptExecutor {


    void beforeFlow(ScriptContext ctx);

    Object execute(String script, ScriptContext ctx);

    void afterFlow(ScriptContext ctx);


}
