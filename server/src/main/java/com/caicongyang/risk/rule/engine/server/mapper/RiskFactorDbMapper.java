package com.caicongyang.risk.rule.engine.server.mapper;

import com.caicongyang.risk.rule.engine.server.domain.RiskFactorDb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caicongyang
 * @since 2024-09-04
 */
public interface RiskFactorDbMapper extends BaseMapper<RiskFactorDb> {

    /**
     * 自定义插入方法
     *
     * @param name 因子名称
     * @param factorCode 因子编码
     * @param type 类型
     * @param script 脚本
     * @param scriptType 脚本类型
     * @param descript 描述
     * @param createUserCode 创建用户编码
     * @param createUsername 创建用户名
     * @return 成功插入的行数
     */
    @Insert("INSERT INTO risk_factor_db (name, factor_code, type, script, script_type, descript, create_user_code, create_username, create_time, update_time) " +
            "VALUES (#{name}, #{factorCode}, #{type}, #{script}, #{scriptType}, #{descript}, #{createUserCode}, #{createUsername}, NOW(), NOW())")
    int insertRiskFactor(@Param("name") String name,
                         @Param("factorCode") String factorCode,
                         @Param("type") Boolean type,
                         @Param("script") String script,
                         @Param("scriptType") Boolean scriptType,
                         @Param("descript") String descript,
                         @Param("createUserCode") String createUserCode,
                         @Param("createUsername") String createUsername);


                         
}
