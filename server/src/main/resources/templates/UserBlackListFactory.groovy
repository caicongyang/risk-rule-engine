package templates

import com.caicongyang.risk.common.RiskFact
import com.caicongyang.risk.rule.engine.server.rule.factor.GroovyFactBase
import com.caicongyang.risk.rule.engine.server.script.ScriptContext

/**
 * 构建风控因子； 用户黑名单for test
 */
class UserBlackListFactory extends GroovyFactBase {

    /**
     * data for test
     */
    private static List<String, Boolean> userIdBlackList = new ArrayList<>();

    static {
        userIdBlackList.add("123", true);
        userIdBlackList.add("124", false)
    }

    @Override
    void run(RiskFact fact, ScriptContext scriptContext) {

        Map<String, Object> map = scriptContext.getFactorValueMap();
        var userId = fact.getUserId();
        if (userIdBlackList.contains(userId)) {
            map.put("isUserIdBlackList", userIdBlackList.get(userId));
        } else {
            map.put("isUserIdBlackList", false);
        }

    }
}
