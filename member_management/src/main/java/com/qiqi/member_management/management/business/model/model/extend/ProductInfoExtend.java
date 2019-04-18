package com.qiqi.member_management.management.business.model.model.extend;

import com.qiqi.member_management.management.business.model.ProductInfo;

/**
 * ProductInfoExtend类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月17号 20:11
 * @Version 1.0.0
 */
public class ProductInfoExtend extends ProductInfo {

    private String categoryName;

    private Integer productAmount;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }
}
