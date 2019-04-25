package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.OrderRel;
import java.util.List;

public interface OrderRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRel record);

    OrderRel selectByPrimaryKey(Integer id);

    List<OrderRel> selectAll();

    int updateByPrimaryKey(OrderRel record);

    /**
     * getLastOrderRel(获取最新订单关联信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.OrderRel
     * @exception 
     * @Date  2019-04-25 09:56:18
     **/
    OrderRel getLastOrderRel();

    void saveOrderRel(OrderRel orderRel);
}