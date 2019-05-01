package com.qiqi.member_management.management.business.model.model.extend;

import com.qiqi.member_management.management.business.model.IntegralConfig;
import com.qiqi.member_management.management.business.model.MemberInfo;

/**
 * MemberIntegralInfo类简述
 *
 * @Description 会员积分实体
 * @Author shen.chu1
 * @Date 2019年04月28号 8:41
 * @Version 1.0.0
 */
public class MemberIntegralInfo {

    private MemberInfo memberInfo;

    private IntegralConfig integralConfig;

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }

    public IntegralConfig getIntegralConfig() {
        return integralConfig;
    }

    public void setIntegralConfig(IntegralConfig integralConfig) {
        this.integralConfig = integralConfig;
    }
}
