package com.caicongyang.risk.rule.engine.server.service;

import com.caicongyang.risk.rule.engine.server.domain.RiskFactorDb;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caicongyang.risk.rule.engine.server.domain.dto.RiskFactorAddDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caicongyang
 * @since 2024-09-04
 */
public interface IRiskFactorDbService extends IService<RiskFactorDb> {

    /**
     * 保存风控因子
     * @param addDTO 风控因子信息
     * @return 保存结果
     */
    boolean saveRiskFactor(RiskFactorAddDTO addDTO);
    
    /**
     * 根据factorCode检查因子是否存在
     * @param factorCode 因子编码
     * @return 是否存在
     */
    boolean checkFactorCodeExists(String factorCode);
}
