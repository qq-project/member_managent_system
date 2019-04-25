package com.qiqi.member_management.management.business.dto.request;

import com.qiqi.member_management.management.business.model.CategoryInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * ProductInfoUpdateRequestDto类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月22号 14:59
 * @Version 1.0.0
 */
public class ProductInfoUpdateRequestDto {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotEmpty(message = "商品id不能为空")
    private String productId;

    @NotEmpty(message = "库存id不能为空")
    private String storageId;

    @NotEmpty(message = "商品名称不能为空")
    private String productName;

    private List<CategoryInfo> categoryInfoList;

    private BigDecimal price;

    private Integer productAmount;

    private String describute;

    private String productImgSrc;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<CategoryInfo> getCategoryInfoList() {
        return categoryInfoList;
    }

    public void setCategoryInfoList(List<CategoryInfo> categoryInfoList) {
        this.categoryInfoList = categoryInfoList;
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

    public String getProductImgSrc() {
        return productImgSrc;
    }

    public void setProductImgSrc(String productImgSrc) {
        this.productImgSrc = productImgSrc;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
}
