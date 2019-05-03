package com.qiqi.member_management.management.business.vo;

import java.math.BigDecimal;

/**
 * ProductTotalVo类简述
 *
 * @Description 商品销量统计VO
 * @Author shen.chu1
 * @Date 2019年05月03号 12:13
 * @Version 1.0.0
 */
public class ProductTotalVo {

    private String productId;

    private String productName;

    private BigDecimal totalAmount;

    private Integer totalCount;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
