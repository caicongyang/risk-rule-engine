package com.caicongyang.risk.rule.engine.server.domain;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caicongyang
 * @since 2024-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RiskFactorDb对象", description="")
public class RiskFactorDb implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "因子名称")
    private String name;

    @ApiModelProperty(value = "因子code")
    private String factorCode;

    @ApiModelProperty(value = "1: 风控因子code 2. action code")
    private Boolean type;

    @ApiModelProperty(value = "脚本")
    private String script;

    @ApiModelProperty(value = "1: grovvy 2. js")
    private Boolean scriptType;

    @ApiModelProperty(value = "因子说明")
    private String descript;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新用户编码")
    private String updateUserCode;

    @ApiModelProperty(value = "创建用户编码")
    private String createUserCode;

    @ApiModelProperty(value = "更新用户名")
    private String updateUsername;

    @ApiModelProperty(value = "创建用户名")
    private String createUsername;


}
