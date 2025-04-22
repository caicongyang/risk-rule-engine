package com.caicongyang.risk.rule.engine.server.mapper;

import com.caicongyang.risk.rule.engine.server.domain.RiskRuleConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caicongyang
 * @since 2024-05-11
 */
public interface RiskRuleConfigMapper extends BaseMapper<RiskRuleConfig> {
    
    /**
     * 自定义插入方法
     *
     * @param sceneCode 场景编码
     * @param code 规则编码
     * @param factorCode 因子编码
     * @param operator 操作符
     * @param factorCodeValue 因子编码值
     * @param actionCode 动作编码
     * @param deleted 是否删除
     * @param available 是否可用
     * @param createUserCode 创建用户编码
     * @param createUsername 创建用户名
     * @return 成功插入的行数
     */
    @Insert("INSERT INTO risk_rule_config (scene_code, code, factor_code, operator, factor_code_value, action_code, deleted, available, create_user_code, create_username, create_time, update_time) " +
            "VALUES (#{sceneCode}, #{code}, #{factorCode}, #{operator}, #{factorCodeValue}, #{actionCode}, #{deleted}, #{available}, #{createUserCode}, #{createUsername}, NOW(), NOW())")
    int insertRiskRuleConfig(@Param("sceneCode") String sceneCode,
                             @Param("code") String code,
                             @Param("factorCode") String factorCode,
                             @Param("operator") String operator,
                             @Param("factorCodeValue") String factorCodeValue,
                             @Param("actionCode") String actionCode,
                             @Param("deleted") Integer deleted,
                             @Param("available") Integer available,
                             @Param("createUserCode") String createUserCode,
                             @Param("createUsername") String createUsername);
}
