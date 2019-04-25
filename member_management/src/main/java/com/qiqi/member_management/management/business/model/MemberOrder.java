package com.qiqi.member_management.management.business.model;

import java.math.BigDecimal;
import java.util.Date;

public class MemberOrder {
    private Integer id;

    private String orderRelId;

    private String memberId;

    private BigDecimal orderTotalPay;

    private BigDecimal realTotalPay;

    private Integer orderTotalAmount;

    private String remark;

    private Date addedTime;

    private String addedByName;

    private Date lastModifiedTime;

    private String lastModifedByName;

    private String valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderRelId() {
        return orderRelId;
    }

    public void setOrderRelId(String orderRelId) {
        this.orderRelId = orderRelId == null ? null : orderRelId.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public BigDecimal getOrderTotalPay() {
        return orderTotalPay;
    }

    public void setOrderTotalPay(BigDecimal orderTotalPay) {
        this.orderTotalPay = orderTotalPay;
    }

    public BigDecimal getRealTotalPay() {
        return realTotalPay;
    }

    public void setRealTotalPay(BigDecimal realTotalPay) {
        this.realTotalPay = realTotalPay;
    }

    public Integer getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Integer orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public String getAddedByName() {
        return addedByName;
    }

    public void setAddedByName(String addedByName) {
        this.addedByName = addedByName == null ? null : addedByName.trim();
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getLastModifedByName() {
        return lastModifedByName;
    }

    public void setLastModifedByName(String lastModifedByName) {
        this.lastModifedByName = lastModifedByName == null ? null : lastModifedByName.trim();
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }
}