package com.qiqi.member_management.management.business.vo;

import java.math.BigDecimal;

/**
 * IntegralAmountVo类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年05月01号 15:53
 * @Version 1.0.0
 */
public class IntegralAmountVo {

    private BigDecimal integralMaxAmount;

    private  BigDecimal discount;

    public BigDecimal getIntegralMaxAmount() {
        return integralMaxAmount;
    }

    public void setIntegralMaxAmount(BigDecimal integralMaxAmount) {
        this.integralMaxAmount = integralMaxAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
