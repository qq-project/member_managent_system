package com.qiqi.member_management.common.exception;

/**
 * BaseException类简述
 * 基础异常类(继承RuntimeException)
 * @Description
 * @Author qiqi
 * @Date 2019年04月06号 12:08
 * @Version 1.0.0
 */
public class BaseException extends RuntimeException{

    private String msg;
    private String code;

    public BaseException(Object code){
        // 继承父类的构造方法
        super(MsgManagement.getMsg(code));
        this.code = String.valueOf(code);
        this.msg = MsgManagement.getMsg(code);
    }
    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public BaseException(Object code, String... message) {
        super(MsgManagement.getMsg(code,message));
        this.code = String.valueOf(code);
        this.msg = MsgManagement.getMsg(code,message);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
