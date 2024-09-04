package com.caicongyang.risk.rule.engine.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caicongyang.core.basic.Result;
import com.caicongyang.risk.rule.engine.server.domain.RiskFactorDb;
import com.caicongyang.risk.rule.engine.server.domain.dto.RiskFactorDBQueryDTO;
import com.caicongyang.risk.rule.engine.server.service.IRiskFactorDbService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 *  风控因子库
 * </p>
 *
 * @author caicongyang
 * @since 2024-09-04
 */
@RestController
@RequestMapping("/risk-factor-db")
public class RiskFactorDbController {



    @Autowired
    IRiskFactorDbService factorDbService;

    @ApiOperation(value = "风控因子列表")
    @GetMapping("/list")
    Result<IPage<RiskFactorDb>> getList(@ApiParam("开始页面") @RequestParam("startPage") Integer startPage,
                                        @ApiParam("页面大小") @RequestParam("pageSize") Integer pageSize,
                                        @ApiParam("请求入参数") @RequestBody RiskFactorDBQueryDTO riskFactorDb) {

        if (startPage == null) {
            startPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        IPage<RiskFactorDb> page = new Page<>();
        page.setCurrent(startPage);
        page.setSize(pageSize);
        LambdaQueryWrapper<RiskFactorDb> eq = new LambdaQueryWrapper<RiskFactorDb>().eq(!Objects.isNull(riskFactorDb.getName()), RiskFactorDb::getName, riskFactorDb.getName())
                .eq(Objects.nonNull(riskFactorDb.getType()), RiskFactorDb::getType, riskFactorDb.getType())
                .eq(Objects.nonNull(riskFactorDb.getFactorCode()), RiskFactorDb::getFactorCode, riskFactorDb.getFactorCode());
        page = factorDbService.page(page, eq);

        return Result.ok(page);
    }
}
