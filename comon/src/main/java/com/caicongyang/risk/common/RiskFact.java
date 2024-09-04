package com.caicongyang.risk.common;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class RiskFact implements Serializable {


    @NotEmpty(message = "sceneCode不能为空")
    private String sceneCode;

    @NotEmpty(message = "userId不能为空")
    private String userId;

    @NotEmpty(message = "mobile不能为空")
    private String mobile;

    @NotEmpty(message = "requestCode不能为空")
    private String requestCode;

    @NotEmpty(message = "ip不能为空")
    private String ip;

    private Boolean fireOne = false;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public Boolean getFireOne() {
        return fireOne;
    }

    public void setFireOne(Boolean fireOne) {
        this.fireOne = fireOne;
    }
}
