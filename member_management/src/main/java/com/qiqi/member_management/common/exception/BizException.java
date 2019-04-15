package com.qiqi.member_management.common.exception;

/**
 * BizException类简述
 * 自定义异常处理类(针对业务异常进行处理)
 * @Description
 * @Author qiqi
 * @Date 2019年04月06号 12:07
 * @Version 1.0.0
 */
public class BizException extends BaseException {
    /** 序列化编号*/
    private static final long serialVersionUID = 1L;
    public BizException(Object code) {
        super(code);
    }
    public BizException(String msg) {
        super(msg);;
    }
    public BizException(Object code, String... message) {
        super(code,message);
    }
    public BizException(Exception e) {
        super(e.getMessage());
    }
    public BizException(Object code, Exception e) {
        super(code, e.getMessage());
    }
    
}
