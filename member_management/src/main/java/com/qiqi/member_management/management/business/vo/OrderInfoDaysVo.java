package com.qiqi.member_management.management.business.vo;

import java.math.BigDecimal;

/**
 * OrderInfoDaysVo类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年05月03号 13:46
 * @Version 1.0.0
 */
public class OrderInfoDaysVo {

    private String orderId;

    private String memberName;
    
    private String memberId;

    private BigDecimal totalAmount;

    private Integer totalCount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
