package com.caicongyang.risk.rule.engine.server.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 风控规则配置新增DTO
 * </p>
 *
 * @author caicongyang
 * @since 2024-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RiskRuleConfigAddDTO对象", description = "风控规则配置新增对象")
public class RiskRuleConfigAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "场景编码不能为空")
    @ApiModelProperty(value = "场景编码", required = true)
    private String sceneCode;

    @NotBlank(message = "规则编码不能为空")
    @ApiModelProperty(value = "规则编码", required = true)
    private String code;

    @NotBlank(message = "因子编码不能为空")
    @ApiModelProperty(value = "因子编码", required = true)
    private String factorCode;

    @NotBlank(message = "操作符不能为空")
    @ApiModelProperty(value = "操作符", required = true)
    private String operator;

    @NotBlank(message = "因子编码值不能为空")
    @ApiModelProperty(value = "因子编码值", required = true)
    private String factorCodeValue;

    @NotBlank(message = "动作编码不能为空")
    @ApiModelProperty(value = "动作编码", required = true)
    private String actionCode;

    @ApiModelProperty(value = "是否删除 0-正常 1-已删除")
    private Integer deleted = 0;

    @ApiModelProperty(value = "是否可用，0-不可用，1-可用")
    private Integer available = 1;

    @ApiModelProperty(value = "创建用户编码")
    private String createUserCode;

    @ApiModelProperty(value = "创建用户名")
    private String createUsername;
    
    // Getters and Setters
    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getFactorCodeValue() {
        return factorCodeValue;
    }

    public void setFactorCodeValue(String factorCodeValue) {
        this.factorCodeValue = factorCodeValue;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }
} 