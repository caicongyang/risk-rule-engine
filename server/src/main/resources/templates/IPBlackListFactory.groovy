package templates

import com.caicongyang.risk.common.RiskFact
import com.caicongyang.risk.rule.engine.server.rule.factor.GroovyFactBase
import com.caicongyang.risk.rule.engine.server.script.ScriptContext

/**
 * 构建风控因子  ip 黑名单 for test
 */
class IPBlackListFactory extends GroovyFactBase {

    /**
     * data for test
     */
    private static List<String, Boolean> ipBlackList = new ArrayList<>();

    static {
        ipBlackList.add("114.114.114.114", true);
        ipBlackList.add("8.8.8.8", false)
    }

    @Override
    void run(RiskFact fact, ScriptContext scriptContext) {

        Map<String, Object> map = scriptContext.getFactorValueMap();
        var userId = fact.getUserId();
        if (ipBlackList.contains(userId)) {
            map.put("isIPBlackList", ipBlackList.get(userId));
        } else {
            map.put("isIPBlackList", false);
        }
    }
}
