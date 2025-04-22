# 风控规则引擎

一个灵活的风控规则引擎，允许基于不同业务场景动态配置和执行风控规则。

该系统通过可配置的规则引擎提供了定义风控场景、风控因子和风控动作的机制。它使用Groovy脚本实现动态规则评估和执行。

## 核心功能

- 配置具有特定风控因子的风控场景
- 定义包含条件和动作的风控规则
- 通过Groovy脚本进行动态规则评估
- 基于用户输入参数的实时风险评估
- 灵活的响应处理（通过、拒绝或需要验证）

## 使用指南

### 系统结构

系统主要由以下几部分组成：
1. **风控场景** - 不同的业务场景（RiskScene）
2. **风控因子** - 风控评估的基本单元（RiskFactorDb）
3. **风控规则** - 将因子和动作连接起来的规则（RiskRuleConfig）
4. **风控动作** - 规则命中后执行的动作（存储在RiskFactorDb中，类型为action）

### 创建风控场景

1. 使用 `/risk-scene` API创建风控场景
2. 设置唯一的场景编码（SceneCode）和场景名称

### 配置风控因子

1. 通过 `/risk-factor-db` API配置风控因子
2. 每个因子需要配置：
   - 因子名称（name）
   - 因子编码（factorCode）
   - 类型（type）：1表示风控因子，2表示动作
   - 脚本（script）：Groovy脚本实现逻辑

### 风控因子脚本编写

创建风控因子时，需要编写Groovy脚本实现因子逻辑，以下是基类：

```groovy
// 继承GroovyFactBase基类
import com.caicongyang.risk.common.RiskFact
import com.caicongyang.risk.rule.engine.server.rule.factor.GroovyFactBase
import com.caicongyang.risk.rule.engine.server.script.ScriptContext

class MyFactorImplementation extends GroovyFactBase {
    @Override
    void run(RiskFact fact, ScriptContext context) {
        // 实现因子逻辑
        // 例如：检查用户IP是否在黑名单中
        String ip = fact.getIp()
        // 将结果放入context中
        context.getFactorValueMap().put("myFactorCode", true/false)
    }
}
```

### 配置风控规则

1. 使用 `/risk-rule-config` API配置风控规则
2. 每条规则包含：
   - 场景编码（sceneCode）
   - 规则编码（code）
   - 因子编码（factorCode）
   - 操作符（operator）
   - 因子值（factorCodeValue）
   - 动作编码（actionCode）

### 风控动作脚本编写

创建风控动作时，需要编写Groovy脚本实现动作逻辑，以下是基类：

```groovy
// 继承GroovyVerifiedBase基类
import com.caicongyang.risk.common.RiskFact
import com.caicongyang.risk.common.RiskResult
import com.caicongyang.risk.rule.engine.server.rule.action.GroovyVerifiedBase
import com.caicongyang.risk.rule.engine.server.script.ScriptContext

class MyActionImplementation extends GroovyVerifiedBase {
    @Override
    RiskResult run(RiskFact fact, ScriptContext context) {
        // 实现动作逻辑
        // 例如：拒绝高风险请求或者返回通过
        return RiskResult.reject() // 或者 RiskResult.pass()
    }
}
```

### 执行风控评估

1. 调用 `/v1/risk` API进行风控评估
2. 请求参数需要包含：
   ```json
   {
     "sceneCode": "your_scene_code",
     "userId": "user_id",
     "mobile": "phone_number",
     "requestCode": "request_id",
     "ip": "user_ip",
     "fireOne": true  // 是否只触发第一条命中的规则
   }
   ```
3. 系统会返回风控结果：通过、拒绝或需要进一步验证

### 风控结果说明

- **code**: 风控情况（0-拒绝，1-通过，2-待校验）
- **verifyCode**: 校验类型（0-不需要校验，1-短信，2-人脸，3-银行卡，4-身份证）
- **msg**: 返回消息 