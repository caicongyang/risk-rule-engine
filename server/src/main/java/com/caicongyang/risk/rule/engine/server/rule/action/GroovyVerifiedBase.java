package com.caicongyang.risk.rule.engine.server.rule.action;

import com.caicongyang.risk.common.RiskFact;
import com.caicongyang.risk.common.RiskResult;
import com.caicongyang.risk.rule.engine.server.script.ScriptContext;

public abstract class GroovyVerifiedBase {

    public abstract RiskResult run(RiskFact fact, ScriptContext context);

}
