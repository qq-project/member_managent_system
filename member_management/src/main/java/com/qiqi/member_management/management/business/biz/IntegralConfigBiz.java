package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.management.business.mapper.IntegralConfigMapper;
import com.qiqi.member_management.management.business.model.IntegralConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IntegralConfigBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月25号 9:22
 * @Version 1.0.0
 */
@Service
public class IntegralConfigBiz {

    @Autowired
    private IntegralConfigMapper integralConfigMapper;
    /**
     * queryInfoByIntegral(根据积分确定会员等级和折扣)
     *
     * @Param 
     * @param integral
     * @return com.qiqi.member_management.management.business.model.IntegralConfig
     * @exception 
     * @Date  2019-04-25 09:23:49
     **/
    public IntegralConfig queryInfoByIntegral(Integer integral){
        return integralConfigMapper.queryIntegralConfig(integral);
    }
}
