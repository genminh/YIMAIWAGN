package com.easybuy.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *MD5加密的工具类
 */

public class SecurityUtils {
    /**
     * MD5加密操作
     * @param value
     * @return
     */
    public static String md5Hex(String value){
        return DigestUtils.md5Hex(value);
    }

    /**
     * MD5   3次加密
     * @param value
     * @return
     */
    public static String md5Hex3(String value){
        for (int i = 0; i < 0; i++) {
            value = DigestUtils.md5Hex(value);
        }
        return value;
    }

}
