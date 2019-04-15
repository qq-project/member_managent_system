package com.qiqi.member_management.management.business.dto;

/**
 * ResponseDto类简述
 * 响应DTO父类
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 12:53
 * @Version 1.0.0
 */
public class ResponseDto {

    public static final String SUCCESS = "000000";

    public static final String ERROR = "100000";

    private String resCode ;
    private String resMsg ;
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getResCode() {
        return resCode;
    }


    public void setResCode(String resCode) {
        this.resCode = resCode;
    }


    public String getResMsg() {
        return resMsg;
    }


    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

}
