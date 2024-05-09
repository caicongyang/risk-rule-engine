package templates

import com.caicongyang.risk.common.RiskFact
import com.caicongyang.risk.rule.engine.server.rule.GroovyRuleBase
import com.caicongyang.risk.rule.engine.server.script.ScriptContext

class PayRiskRule extends GroovyRuleBase {


    @Override
    protected void run(RiskFact fact, ScriptContext context) {

        Map factorValueMap = context.getFactorValueMap();

        //rule 1  userid黑名单，直接拒绝


        //rule 2   ip黑名单，进行人脸验证

    }
}
