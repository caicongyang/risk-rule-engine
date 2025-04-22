package com.caicongyang.risk.rule.engine.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.caicongyang.risk.rule.engine.server.domain.RiskRuleConfig;
import com.caicongyang.risk.rule.engine.server.domain.dto.RiskRuleConfigAddDTO;
import com.caicongyang.risk.rule.engine.server.mapper.RiskRuleConfigMapper;
import com.caicongyang.risk.rule.engine.server.service.IRiskRuleConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caicongyang
 * @since 2024-05-11
 */
@Service
public class RiskRuleConfigServiceImpl extends ServiceImpl<RiskRuleConfigMapper, RiskRuleConfig> implements IRiskRuleConfigService {

    @Override
    public boolean saveRiskRuleConfig(RiskRuleConfigAddDTO addDTO) {
        try {
            // 使用自定义的Mapper方法，直接传递DTO中的属性
            int rows = baseMapper.insertRiskRuleConfig(
                addDTO.getSceneCode(),
                addDTO.getCode(),
                addDTO.getFactorCode(),
                addDTO.getOperator(),
                addDTO.getFactorCodeValue(),
                addDTO.getActionCode(),
                addDTO.getDeleted(),
                addDTO.getAvailable(),
                addDTO.getCreateUserCode(),
                addDTO.getCreateUsername()
            );
            
            // 返回是否插入成功
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean checkRuleCodeExists(String code) {
        // 使用MyBatis Plus的包装类查询
        return this.baseMapper.selectCount(
            Wrappers.<RiskRuleConfig>query().eq("code", code)
        ) > 0;
    }
}
