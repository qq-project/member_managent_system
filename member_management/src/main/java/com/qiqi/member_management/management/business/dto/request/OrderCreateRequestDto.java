package com.qiqi.member_management.management.business.dto.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * OrderCreateRequestDto类简述
 *
 * @Description 创建订单请求DTO
 * @Author shen.chu1
 * @Date 2019年04月25号 8:40
 * @Version 1.0.0
 */
public class OrderCreateRequestDto {

    private Integer id;

    @NotNull(message = "商品ID不能为空")
    private String productId;
    @NotNull(message = "会员ID不能为空")
    private String memberId;

    private BigDecimal price;

    private Integer orderAmount;

    private BigDecimal orderPay;

    /**
     * 订单状态 10 待下单，20 已下单
     **/
    private String orderStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(BigDecimal orderPay) {
        this.orderPay = orderPay;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
