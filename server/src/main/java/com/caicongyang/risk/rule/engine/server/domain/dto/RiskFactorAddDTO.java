package com.caicongyang.risk.rule.engine.server.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 风控因子新增DTO
 * </p>
 *
 * @author caicongyang
 * @since 2024-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RiskFactorAddDTO对象", description = "风控因子新增对象")
public class RiskFactorAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "因子名称不能为空")
    @ApiModelProperty(value = "因子名称", required = true)
    private String name;

    @NotBlank(message = "因子code不能为空")
    @ApiModelProperty(value = "因子code", required = true)
    private String factorCode;

    @NotNull(message = "因子类型不能为空")
    @ApiModelProperty(value = "1: 风控因子code 2. action code", required = true)
    private Boolean type;

    @NotBlank(message = "脚本内容不能为空")
    @ApiModelProperty(value = "脚本", required = true)
    private String script;

    @NotNull(message = "脚本类型不能为空")
    @ApiModelProperty(value = "1: grovvy 2. js", required = true)
    private Boolean scriptType;

    @ApiModelProperty(value = "因子说明")
    private String descript;

    @ApiModelProperty(value = "创建用户编码")
    private String createUserCode;

    @ApiModelProperty(value = "创建用户名")
    private String createUsername;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Boolean getScriptType() {
        return scriptType;
    }

    public void setScriptType(Boolean scriptType) {
        this.scriptType = scriptType;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
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