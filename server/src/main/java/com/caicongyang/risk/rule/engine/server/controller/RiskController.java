package com.caicongyang.risk.rule.engine.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.caicongyang.core.basic.Result;
import com.caicongyang.core.exception.BusinessException;
import com.caicongyang.risk.common.RiskFact;
import com.caicongyang.risk.common.RiskResult;
import com.caicongyang.risk.rule.engine.server.domain.RiskFactorDb;
import com.caicongyang.risk.rule.engine.server.domain.RiskRuleConfig;
import com.caicongyang.risk.rule.engine.server.script.IScriptExecutor;
import com.caicongyang.risk.rule.engine.server.script.ScriptContext;
import com.caicongyang.risk.rule.engine.server.service.IRiskFactorDbService;
import com.caicongyang.risk.rule.engine.server.service.IRiskRuleConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Controller
@RequestMapping("/v1")
public class RiskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RiskController.class);


    @Autowired
    IScriptExecutor grovvyExecutor;

    @Autowired
    IRiskRuleConfigService riskRuleConfigService;

    @Autowired
    IRiskFactorDbService factorDbService;


    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hello，Spring Boot ！";
    }


    @PostMapping(value = "/risk")
    @ResponseBody
    public Result<RiskResult> risk(@RequestBody @Valid RiskFact fact) {
        ScriptContext ctx = new ScriptContext();

        // 场景所涉及所有的因子
        List<RiskRuleConfig> list = riskRuleConfigService.list(new LambdaQueryWrapper<RiskRuleConfig>().eq(RiskRuleConfig::getSceneCode, fact.getSceneCode()));
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(-1, "未配置风控规则，请联系风控运营人员！");
        }
        List<String> factorCodes = list.stream().map(RiskRuleConfig::getFactorCode).collect(Collectors.toList());
        List<RiskFactorDb> factDbList = factorDbService.list(new LambdaQueryWrapper<RiskFactorDb>().in(RiskFactorDb::getFactorCode, factorCodes));

        Map<String, String> factorMap = new HashMap<>(Collections.emptyMap());
        factDbList.forEach(a -> factorMap.put(a.getFactorCode(), a.getScript()));


        // 场景所涉及的action脚本
        List<String> actionCodes = list.stream().map(RiskRuleConfig::getActionCode).collect(Collectors.toList());
        List<RiskFactorDb> actionDbList = factorDbService.list(new LambdaQueryWrapper<RiskFactorDb>().in(RiskFactorDb::getFactorCode, actionCodes));
        Map<String, String> actionMap = new HashMap<>(Collections.emptyMap());
        actionDbList.forEach(a -> actionMap.put(a.getFactorCode(), a.getScript()));


        // 初始化 ctx
        ctx.setSceneCode(fact.getSceneCode());
        ctx.setFactorCode(factorCodes);
        ctx.setFactorMap(factorMap);
        ctx.setFireOne(fact.getFireOne());
        ctx.setActionMap(actionMap);

        try {
            return Result.ok(grovvyExecutor.execute(fact, ctx));
        } catch (Exception e) {
            LOGGER.error("风控执行异常:{}", fact.getRequestCode(), e);
            return Result.ok(RiskResult.pass("风控执行异常,默认通过！"));
        } finally {
            ctx.clear();
        }
    }

}
