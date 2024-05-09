package com.caicongyang.risk.common;

import com.caicongyang.risk.common.code.CodeEnum;
import com.caicongyang.risk.common.code.VerifiedCodeEnum;

import java.io.Serializable;

public class RiskResult implements Serializable {

    /**
     * 风控情况: 0 拒绝， 1 通过， 2 待校验
     */
    private Integer code;

    /**
     * 校验类型： 0 不需要校验， 1 短信  2人脸  3 银行卡 4 身份证
     */
    private Integer verifyCode;

    /**
     * 返回消息
     */
    private String msg;


    public RiskResult(Integer code, Integer verifyCode, String msg) {
        this.code = code;
        this.verifyCode = verifyCode;
        this.msg = msg;
    }

    public static RiskResult pass() {
        return new RiskResult(CodeEnum.PASS.getCode(), VerifiedCodeEnum.NONEEDVERIFIED.getCode(), VerifiedCodeEnum.NONEEDVERIFIED.getMessage());
    }

    public static RiskResult reject() {
        return new RiskResult(CodeEnum.REJECT.getCode(), VerifiedCodeEnum.NONEEDVERIFIED.getCode(), VerifiedCodeEnum.NONEEDVERIFIED.getMessage());
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(Integer verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
