package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.Order;
import com.qiqi.member_management.management.business.vo.OrderListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * queryOrder(查询订单)
     *
     * @Param 
     * @param order
     * @return com.qiqi.member_management.management.business.model.Order
     * @exception 
     * @Date  2019-04-27 09:21:24
     **/
    Order queryOrder(Order order);

    /**
     * updateOrder(修改订单)
     *
     * @Param 
     * @param order
     * @return void
     * @exception 
     * @Date  2019-04-27 09:43:46
     **/
    void updateOrder(Order order);

    /**
     * queryOrderLists(根据参数查询订单列表)
     *
     * @Param 
     * @param params
     * @return java.util.List<com.qiqi.member_management.management.business.model.Order>
     * @exception 
     * @Date  2019-04-27 11:32:19
     **/
    List<OrderListVo> queryOrderLists(@Param("map") Map<String,Object> params);
}