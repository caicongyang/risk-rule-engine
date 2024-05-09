package templates

import com.caicongyang.risk.common.RiskFact
import com.caicongyang.risk.common.RiskResult
import com.caicongyang.risk.rule.engine.server.rule.action.GroovyVerifiedBase
import com.caicongyang.risk.rule.engine.server.script.ScriptContext

/**
 * 告诉业务方进行何种校验
 */
class BaseRiskVerifiedAction extends GroovyVerifiedBase {


    RiskResult run(RiskFact fact, ScriptContext context) {
        Map<String, RiskResult> fireRuleMap = context.getFireRuleMap();

        if (context.getFireOne()) {
            Set<String> keySet = fireRuleMap.keySet();

            if (!keySet.isEmpty()) {
                String firstKey = keySet.iterator().next();
                return fireRuleMap.get(firstKey);
            } else {
                return RiskResult.pass()
            }
        }
    }
}
