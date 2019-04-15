package com.qiqi.member_management.common.exception;

/**
 * LoginException类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月10号 11:13
 * @Version 1.0.0
 */
public class LoginException extends BaseException {
    /** 序列化编号*/
    private static final long serialVersionUID = 2L;
    public LoginException(Object code) {
        super(code);
    }

    public LoginException(String msg) {
        super(msg);
    }

    public LoginException(Object code, String... message) {
        super(code, message);
    }
    public LoginException(Exception e) {
        super(e.getMessage());
    }
    public LoginException(Object code, Exception e) {
        super(code, e.getMessage());
    }
}
