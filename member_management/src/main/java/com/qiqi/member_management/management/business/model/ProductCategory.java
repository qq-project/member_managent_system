package com.qiqi.member_management.management.business.model;

import java.util.Date;

/**
 * (商品类别关系类)
 *
 * @Param 
 * @param null
 * @return 
 * @exception 
 * @Date  2019-04-22 08:35:35
 **/
public class ProductCategory {
    private Integer id;

    private String productId;

    private String categoryId;

    private String addedByName;

    private Date addedByTime;

    private String lastModifiedByName;

    private Date lastModifiedByTime;

    private String valid;

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
        this.productId = productId == null ? null : productId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getAddedByName() {
        return addedByName;
    }

    public void setAddedByName(String addedByName) {
        this.addedByName = addedByName == null ? null : addedByName.trim();
    }

    public Date getAddedByTime() {
        return addedByTime;
    }

    public void setAddedByTime(Date addedByTime) {
        this.addedByTime = addedByTime;
    }

    public String getLastModifiedByName() {
        return lastModifiedByName;
    }

    public void setLastModifiedByName(String lastModifiedByName) {
        this.lastModifiedByName = lastModifiedByName == null ? null : lastModifiedByName.trim();
    }

    public Date getLastModifiedByTime() {
        return lastModifiedByTime;
    }

    public void setLastModifiedByTime(Date lastModifiedByTime) {
        this.lastModifiedByTime = lastModifiedByTime;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }
}