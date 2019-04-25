package com.qiqi.member_management.management.business.dto.request;

import com.qiqi.member_management.management.business.model.CategoryInfo;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/**
 * ProductInfoSaveRequestDto类简述
 * 新增商品请求DTO
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月22号 8:36
 * @Version 1.0.0
 */
public class ProductInfoSaveRequestDto {

    @NotEmpty(message = "商品名称不能为空")
    private String productName;

    private List<CategoryInfo> categoryIdList;

    private BigDecimal price;

    private Integer productAmount;

    private String describute;

    private String productSrc;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public String getDescribute() {
        return describute;
    }

    public void setDescribute(String describute) {
        this.describute = describute;
    }

    public List<CategoryInfo> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<CategoryInfo> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }

    public String getProductSrc() {
        return productSrc;
    }

    public void setProductSrc(String productSrc) {
        this.productSrc = productSrc;
    }
}
