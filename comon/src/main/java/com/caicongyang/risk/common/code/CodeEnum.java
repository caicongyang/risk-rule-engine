package com.caicongyang.risk.common.code;

public enum CodeEnum {

    PASS(0, "pass"),
    REJECT(1, "reject"),
    VERIFIED(2, "need verified");


    private Integer code;
    private String message;


    private CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
