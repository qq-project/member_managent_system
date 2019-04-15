package com.qiqi.member_management.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * MD5Util类简述
 * 密码加密工具类
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 14:21
 * @Version 1.0.0
 */
public final class MD5Util {
    protected MD5Util(){}

    private static final String SALT = "qiqi"; // 密码加盐

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    /**
     * encypt(密码加密)
     *  SALT + password
     * @Param
     * @param password
     * @return java.lang.String
     * @exception
     * @Date  2019-04-06 14:23:34
     **/
    public static String encypt(String password){
        return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(SALT),HASH_ITERATIONS).toHex();
    }

    /**
     * 用户名 + 密码 加密
     * salt  username.toLowerCase() + qiqi
     *  username + salt + password
     * @param username
     * @param pswd
     * @return
     */
    public static String encrypt(String username, String pswd) {
        return new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username.toLowerCase() + SALT),
                HASH_ITERATIONS).toHex();
    }
}
