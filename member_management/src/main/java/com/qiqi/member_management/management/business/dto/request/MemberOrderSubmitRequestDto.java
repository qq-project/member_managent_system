package com.qiqi.member_management.management.business.dto.request;

import com.qiqi.member_management.management.business.model.Order;

import java.util.List;

/**
 * MemberOrderSubmitRequestDto类简述
 *
 * @Description 购物车下单请求DTO
 * @Author shen.chu1
 * @Date 2019年04月28号 8:20
 * @Version 1.0.0
 */
public class MemberOrderSubmitRequestDto {

    private List<Order> orderCarts;

    public List<Order> getOrderCarts() {
        return orderCarts;
    }

    public void setOrderCarts(List<Order> orderCarts) {
        this.orderCarts = orderCarts;
    }
}
