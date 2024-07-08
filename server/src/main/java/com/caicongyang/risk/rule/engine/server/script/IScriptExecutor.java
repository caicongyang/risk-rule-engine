package com.caicongyang.risk.rule.engine.server.script;

import com.caicongyang.risk.common.RiskFact;
import com.caicongyang.risk.common.RiskResult;
import com.caicongyang.risk.rule.engine.server.domain.RiskRecord;
import com.caicongyang.risk.rule.engine.server.utils.JsonUtils;

import java.lang.reflect.InvocationTargetException;

public interface IScriptExecutor {


    public default RiskResult execute(RiskFact fact, ScriptContext ctx) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        beforeFlow(fact, ctx);
        RiskResult result = run(fact, ctx);
        afterFlow(fact, ctx,result);
        return result;
    }

    default void beforeFlow(RiskFact fact, ScriptContext ctx) {

    }

    RiskResult run(RiskFact fact, ScriptContext ctx) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    default void afterFlow(RiskFact fact, ScriptContext ctx,RiskResult result ) {


    }


}
