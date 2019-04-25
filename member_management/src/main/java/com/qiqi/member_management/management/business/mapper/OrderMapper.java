package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.Order;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);

    /**
     * getLastOrderInfo(获取最新的订单信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.Order
     * @exception 
     * @Date  2019-04-25 08:45:33
     **/
    Order getLastOrderInfo();

    /**
     * saveOrder(创建订单)
     *
     * @Param 
     * @param order
     * @return void
     * @exception 
     * @Date  2019-04-25 09:45:07
     **/
    void saveOrder(Order order);
}