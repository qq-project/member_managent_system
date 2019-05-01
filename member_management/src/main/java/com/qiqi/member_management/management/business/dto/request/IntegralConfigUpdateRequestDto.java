package com.qiqi.member_management.management.business.dto.request;

import com.qiqi.member_management.management.business.model.IntegralConfig;

import java.math.BigDecimal;
import java.util.List;

/**
 * IntegralConfigUpdateRequestDto类简述
 *
 * @Description 更改积分配置信息
 * @Author shen.chu1
 * @Date 2019年05月01号 15:18
 * @Version 1.0.0
 */
public class IntegralConfigUpdateRequestDto {

    private List<IntegralConfig> integralConfigList;

    private BigDecimal rate;

    public List<IntegralConfig> getIntegralConfigList() {
        return integralConfigList;
    }

    public void setIntegralConfigList(List<IntegralConfig> integralConfigList) {
        this.integralConfigList = integralConfigList;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
