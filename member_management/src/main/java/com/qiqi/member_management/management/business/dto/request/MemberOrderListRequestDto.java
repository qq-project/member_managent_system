package com.qiqi.member_management.management.business.dto.request;

/**
 * MemberOrderListRequestDto类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月27号 10:52
 * @Version 1.0.0
 */
public class MemberOrderListRequestDto {

    private String orderType;

    private String memberName;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
