package com.caicongyang.risk.rule.engine.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caicongyang.core.basic.Result;
import com.caicongyang.risk.rule.engine.server.domain.RiskRecord;
import com.caicongyang.risk.rule.engine.server.domain.RiskRuleConfig;
import com.caicongyang.risk.rule.engine.server.service.IRiskRuleConfigService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caicongyang
 * @since 2024-05-10
 */
@RestController
@RequestMapping("/risk-rule-config")
public class RiskRuleConfigController {


    @Autowired
    IRiskRuleConfigService ruleConfigService;


    @ApiOperation(value = "根据code查看风控规则详情")
    @GetMapping("/get-by-code")
    Result<RiskRuleConfig> getByCode(@ApiParam("") @RequestParam("code") String code) {

        RiskRuleConfig config = ruleConfigService.getOne(new LambdaQueryWrapper<RiskRuleConfig>().eq(RiskRuleConfig::getCode, code));

        return Result.ok(config);
    }

}
