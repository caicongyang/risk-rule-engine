package com.caicongyang.risk.rule.engine.server.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value = "RiskFactorDb查询对象", description = "")
public class RiskFactorDBQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "因子名称")
    private String name;

    @ApiModelProperty(value = "因子code")
    private String factorCode;

    @ApiModelProperty(value = "1: 风控因子code 2. action code")
    private Boolean type;


    @ApiModelProperty(value = "1: grovvy 2. js")
    private Boolean scriptType;


}
