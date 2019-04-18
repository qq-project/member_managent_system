package com.qiqi.member_management.management.business.dto.request;

/**
 * ProductInfoListRequestDto类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月17号 19:39
 * @Version 1.0.0
 */
public class ProductInfoListRequestDto {

    private Integer id;

    private String productName;

    private String categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
