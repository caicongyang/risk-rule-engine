package com.caicongyang.risk.rule.engine.server.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caicongyang.core.basic.Result;
import com.caicongyang.core.exception.BusinessException;
import com.caicongyang.risk.rule.engine.server.domain.RiskFactorDb;
import com.caicongyang.risk.rule.engine.server.domain.dto.RiskFactorAddDTO;
import com.caicongyang.risk.rule.engine.server.domain.dto.RiskFactorDBQueryDTO;
import com.caicongyang.risk.rule.engine.server.service.IRiskFactorDbService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                                        @ApiParam("请求入参数") @RequestBody RiskFactorDBQueryDTO queryDTO) {

        if (startPage == null) {
            startPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        IPage<RiskFactorDb> page = new Page<>();
        page.setCurrent(startPage);
        page.setSize(pageSize);
        
        // 执行分页查询，简化条件构建
        page = factorDbService.page(page);
        return Result.ok(page);
    }
    
    @ApiOperation(value = "新增风控因子")
    @PostMapping("/add")
    public Result<Boolean> addRiskFactor(@ApiParam("风控因子信息") @RequestBody @Valid RiskFactorAddDTO addDTO) {
        // 检查因子code是否已存在
        if (factorDbService.checkFactorCodeExists(addDTO.getFactorCode())) {
            throw new BusinessException(-1, "因子编码已存在，请更换");
        }

        // 调用服务保存风控因子
        boolean success = factorDbService.saveRiskFactor(addDTO);
        
        return Result.ok(success);
    }
}
