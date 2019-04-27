package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.dto.request.MemberOrderListRequestDto;
import com.qiqi.member_management.management.business.dto.request.OrderCreateRequestDto;
import com.qiqi.member_management.management.business.model.MemberOrder;
import org.apache.ibatis.annotations.Param;

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

    /**
     * queryMemberOrderList(查询所有会员订单信息)
     *
     * @Param 
     * @param requestDto
     * @return java.util.List<com.qiqi.member_management.management.business.model.MemberOrder>
     * @exception 
     * @Date  2019-04-27 11:03:26
     **/
    List<MemberOrder> queryMemberOrderList(@Param("params") MemberOrderListRequestDto requestDto);

    /**
     * queryMemberOrder(查询会员信息)
     *
     * @Param 
     * @param memberOrder
     * @return com.qiqi.member_management.management.business.model.MemberOrder
     * @exception 
     * @Date  2019-04-27 12:39:52
     **/
    MemberOrder queryMemberOrder(MemberOrder memberOrder);

    /**
     * getLastOrderInfo(获取最新会员信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.request.OrderCreateRequestDto
     * @exception 
     * @Date  2019-04-27 12:52:55
     **/
    MemberOrder getLastOrderInfo();
}