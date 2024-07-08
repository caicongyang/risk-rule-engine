package com.caicongyang.risk.rule.engine.server.rule.factor

import com.caicongyang.risk.common.RiskFact
import com.caicongyang.risk.rule.engine.server.script.ScriptContext

/**
 * 因子base类
 */
public abstract class GroovyFactBase {


    abstract void run(RiskFact fact, ScriptContext context);

}
