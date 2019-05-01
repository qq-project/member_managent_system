package com.qiqi.member_management.management.business.vo;

import com.qiqi.member_management.management.business.model.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * MemberOrderListVo类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月27号 10:57
 * @Version 1.0.0
 */
public class MemberOrderListVo {

    /**
     * 会员主键
     **/
    private Integer id;

    private String memberName;

    private String memberId;
    
    private BigDecimal totalAmount;

    private BigDecimal totalOrderPay;

    private BigDecimal discount;

    private String orderTime;

    List<OrderListVo> orderCart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalOrderPay() {
        return totalOrderPay;
    }

    public void setTotalOrderPay(BigDecimal totalOrderPay) {
        this.totalOrderPay = totalOrderPay;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public List<OrderListVo> getOrderCart() {
        return orderCart;
    }

    public void setOrderCart(List<OrderListVo> orderCart) {
        this.orderCart = orderCart;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
