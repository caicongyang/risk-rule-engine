package com.caicongyang.risk.rule.engine.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caicongyang.core.basic.Result;
import com.caicongyang.core.exception.BusinessException;
import com.caicongyang.risk.rule.engine.server.domain.RiskRuleConfig;
import com.caicongyang.risk.rule.engine.server.domain.dto.RiskRuleConfigAddDTO;
import com.caicongyang.risk.rule.engine.server.service.IRiskRuleConfigService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 风控规则控制器
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
    Result<RiskRuleConfig> getByCode(@ApiParam(value = "规则code", required = true) @RequestParam("code") String code) {
        // 使用原生QueryWrapper，避免使用方法引用
        QueryWrapper<RiskRuleConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", code);
        RiskRuleConfig config = ruleConfigService.getOne(queryWrapper);

        return Result.ok(config);
    }


    @ApiOperation(value = "根据sceneCode查看风控规则")
    @GetMapping("/get-rule-by-sceneCode")
    Result<List<RiskRuleConfig>> getRuleBySceneCode(@ApiParam(value = "场景code", required = true) @RequestParam("sceneCode") String sceneCode) {
        // 使用原生QueryWrapper，避免使用方法引用
        QueryWrapper<RiskRuleConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RiskRuleConfig::getSceneCode, sceneCode);
        List<RiskRuleConfig> list = ruleConfigService.list(queryWrapper);

        return Result.ok(list);
    }
    
    @ApiOperation(value = "新增风控规则")
    @PostMapping("/add")
    public Result<Boolean> addRiskRuleConfig(@ApiParam("风控规则信息") @RequestBody @Valid RiskRuleConfigAddDTO addDTO) {
        // 检查规则编码是否已存在
        if (ruleConfigService.checkRuleCodeExists(addDTO.getCode())) {
            throw new BusinessException(-1, "规则编码已存在，请更换");
        }

        // 调用服务保存风控规则
        boolean success = ruleConfigService.saveRiskRuleConfig(addDTO);
        
        return Result.ok(success);
    }
}
