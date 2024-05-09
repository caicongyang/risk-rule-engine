package com.caicongyang.risk.rule.engine.server.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 编码工具类
 */
public class MD5Utils {


    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    public static String md5Hex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }
}
