package com.caicongyang.risk.rule.engine.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caicongyang.core.basic.Result;
import com.caicongyang.risk.rule.engine.server.domain.RiskFactorDb;
import com.caicongyang.risk.rule.engine.server.domain.RiskRuleConfig;
import com.caicongyang.risk.rule.engine.server.domain.RiskScene;
import com.caicongyang.risk.rule.engine.server.service.IRiskSceneService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caicongyang
 * @since 2024-09-04
 */
@RestController
@RequestMapping("/risk-scene")
public class RiskSceneController {


    @Autowired
    IRiskSceneService riskSceneService;

    @ApiOperation(value = "场景scene列表")
    @GetMapping("/page")
    Result<List<RiskRuleConfig>> list(@ApiParam("开始页面") @RequestParam("startPage") Integer startPage,
                                      @ApiParam("页面大小") @RequestParam("pageSize") Integer pageSize,) {

        if (startPage == null) {
            startPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        IPage<RiskScene> page = new Page<>();
        page.setCurrent(startPage);
        page.setSize(pageSize);
        page = riskSceneService.page(page);
        return Result.ok(page);
    }



    @ApiOperation(value = "scen明细")
    @GetMapping("/get-by-id")
    Result<RiskScene> list(@ApiParam("id") @RequestParam("id") Integer id) {


        RiskScene risk = riskSceneService.getById(id);
        return Result.ok(risk);
    }

}
