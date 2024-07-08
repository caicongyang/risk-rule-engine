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
 * @since 2024-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RiskRecord对象", description="")
public class RiskRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sceneCode;

    private String requestCode;

    private String requestJson;

    private String fireRuleJson;

    private String resultJson;

    @ApiModelProperty(value = "是否删除 0-正常 1-已删除")
    private Integer deleted;

    @ApiModelProperty(value = "是否可用，0-不可用，1-可用")
    private Integer available;

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
