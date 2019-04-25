package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.MemberOrder;
import java.util.List;

public interface MemberOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberOrder record);

    MemberOrder selectByPrimaryKey(Integer id);

    List<MemberOrder> selectAll();

    int updateByPrimaryKey(MemberOrder record);

    /**
     * saveMemberOrder(保存会员订单信息)
     *
     * @Param 
     * @param memberOrder
     * @return void
     * @exception 
     * @Date  2019-04-25 10:15:53
     **/
    void saveMemberOrder(MemberOrder memberOrder);
}