package com.qiqi.member_management.management.business.model;

import java.math.BigDecimal;
import java.util.Date;

public class IntegralConfig {
    private Integer id;

    private String integralId;

    private Integer integralMinAmount;

    private Integer integralMaxAmount;

    private Integer level;

    private BigDecimal discount;

    private String addedByName;

    private Date addedTime;

    private String lastModifiedByName;

    private Date lastModifiedTime;

    private String valid;

    private BigDecimal rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntegralId() {
        return integralId;
    }

    public void setIntegralId(String integralId) {
        this.integralId = integralId == null ? null : integralId.trim();
    }

    public Integer getIntegralMinAmount() {
        return integralMinAmount;
    }

    public void setIntegralMinAmount(Integer integralMinAmount) {
        this.integralMinAmount = integralMinAmount;
    }

    public Integer getIntegralMaxAmount() {
        return integralMaxAmount;
    }

    public void setIntegralMaxAmount(Integer integralMaxAmount) {
        this.integralMaxAmount = integralMaxAmount;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getAddedByName() {
        return addedByName;
    }

    public void setAddedByName(String addedByName) {
        this.addedByName = addedByName == null ? null : addedByName.trim();
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public String getLastModifiedByName() {
        return lastModifiedByName;
    }

    public void setLastModifiedByName(String lastModifiedByName) {
        this.lastModifiedByName = lastModifiedByName == null ? null : lastModifiedByName.trim();
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}