package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.management.business.mapper.MemberOrderMapper;
import com.qiqi.member_management.management.business.model.MemberOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MemberOrderBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月25号 8:17
 * @Version 1.0.0
 */
@Service
public class MemberOrderBiz {

    @Autowired
    private MemberOrderMapper memberOrderMapper;

    /**
     * saveMemberOrder(保存会员订单信息)
     *
     * @Param 
     * @param memberOrder
     * @return void
     * @exception 
     * @Date  2019-04-25 10:16:09
     **/
    public void saveMemberOrder(MemberOrder memberOrder){
        memberOrderMapper.saveMemberOrder(memberOrder);
    }
}
