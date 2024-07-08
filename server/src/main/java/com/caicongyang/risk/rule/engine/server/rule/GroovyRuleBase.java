package com.caicongyang.risk.rule.engine.server.rule;

import com.caicongyang.risk.common.RiskFact;
import com.caicongyang.risk.rule.engine.server.script.ScriptContext;

/**
 * 规则base 类
 */
public abstract  class GroovyRuleBase {


    protected abstract void run(RiskFact fact, ScriptContext context);
}
