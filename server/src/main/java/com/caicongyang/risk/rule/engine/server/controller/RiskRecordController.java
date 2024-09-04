package com.caicongyang.risk.rule.engine.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caicongyang.risk.rule.engine.server.domain.RiskRecord;
import com.caicongyang.risk.rule.engine.server.service.IRiskRecordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 * 风控记录规则控制器
 * </p>
 *
 * @author caicongyang
 * @since 2024-07-08
 */
@RestController
@RequestMapping("/risk-record")
public class RiskRecordController {


    @Autowired
    IRiskRecordService recordService;

    @ApiOperation(value = "风控请求记录")
    @GetMapping("/list")
    IPage<RiskRecord> getList(@ApiParam("开始页面") @RequestParam("startPage") Integer startPage,
                              @ApiParam("页面大小") @RequestParam("pageSize") Integer pageSize,
                              @ApiParam("请求入参数") @RequestBody RiskRecord record) {

        if (startPage == null) {
            startPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        IPage<RiskRecord> page = new Page<>();
        page.setCurrent(startPage);
        page.setSize(pageSize);
        LambdaQueryWrapper<RiskRecord> eq = new LambdaQueryWrapper<RiskRecord>().eq(!Objects.isNull(record.getSceneCode()), RiskRecord::getSceneCode, record.getSceneCode()).eq(Objects.nonNull(record.getRequestCode()), RiskRecord::getRequestCode, record.getRequestCode());
        page = recordService.page(page, eq);

        return page;
    }

}
