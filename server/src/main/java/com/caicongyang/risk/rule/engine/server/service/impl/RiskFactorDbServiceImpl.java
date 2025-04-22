package com.caicongyang.risk.rule.engine.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.caicongyang.risk.rule.engine.server.domain.RiskFactorDb;
import com.caicongyang.risk.rule.engine.server.domain.dto.RiskFactorAddDTO;
import com.caicongyang.risk.rule.engine.server.mapper.RiskFactorDbMapper;
import com.caicongyang.risk.rule.engine.server.service.IRiskFactorDbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caicongyang
 * @since 2024-09-04
 */
@Service
public class RiskFactorDbServiceImpl extends ServiceImpl<RiskFactorDbMapper, RiskFactorDb> implements IRiskFactorDbService {

    @Override
    public boolean saveRiskFactor(RiskFactorAddDTO addDTO) {
        // 使用我们自定义的Mapper方法，直接传递DTO中的属性
        int rows = baseMapper.insertRiskFactor(
            addDTO.getName(),
            addDTO.getFactorCode(),
            addDTO.getType(),
            addDTO.getScript(),
            addDTO.getScriptType(),
            addDTO.getDescript(),
            addDTO.getCreateUserCode(),
            addDTO.getCreateUsername()
        );
        
        // 返回是否插入成功
        return rows > 0;
    }
    
    @Override
    public boolean checkFactorCodeExists(String factorCode) {
        // 使用MyBatis Plus的包装类查询
        return baseMapper.selectCount(
            Wrappers.<RiskFactorDb>query().eq("factor_code", factorCode)
        ) > 0;
    }
}
