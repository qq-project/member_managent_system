package com.qiqi.member_management.management.business.dto.request;

import java.util.Date;

/**
 * SmsRequestDto类简述
 *
 * @Description 发送短信请求DTO
 * @Author shen.chu1
 * @Date 2019年05月02号 13:24
 * @Version 1.0.0
 */
public class SmsRequestDto {

    private String mobile;

    private String username;

    private String code;

    private String signName;

    private String templateCode;

    private String bizId;

    private Date sendDate;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
