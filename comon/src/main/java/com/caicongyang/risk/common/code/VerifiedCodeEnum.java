package com.caicongyang.risk.common.code;

public enum VerifiedCodeEnum {

    NONEEDVERIFIED(0, "no need verified"),
    MOBILE(1, "mobile msg verification"),
    FACE(2, "face verification"),
    BANKCARD(3, "bank card verification"),
    IDCARD(4, "id card verification");



    private Integer code;
    private String message;


    private VerifiedCodeEnum(Integer code, String message) {
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
