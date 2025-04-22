package com.caicongyang.risk.rule.engine.server.service;

import com.caicongyang.risk.rule.engine.server.domain.RiskRuleConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caicongyang.risk.rule.engine.server.domain.dto.RiskRuleConfigAddDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caicongyang
 * @since 2024-05-11
 */
public interface IRiskRuleConfigService extends IService<RiskRuleConfig> {

    /**
     * 保存风控规则配置
     * @param addDTO 风控规则配置信息
     * @return 保存结果
     */
    boolean saveRiskRuleConfig(RiskRuleConfigAddDTO addDTO);
    
    /**
     * 根据规则编码检查规则是否存在
     * @param code 规则编码
     * @return 是否存在
     */
    boolean checkRuleCodeExists(String code);
}
