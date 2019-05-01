package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.OrderBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.OrderCreateRequestDto;
import com.qiqi.member_management.management.business.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月25号 8:16
 * @Version 1.0.0
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderBiz orderBiz;
    /**
     * createOrder(创建订单请求)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-25 08:41:18
     **/
    @RequestMapping("/createOrder")
    public ResponseDto createOrder(@RequestBody OrderCreateRequestDto requestDto){
        return orderBiz.createOrder(requestDto);
    }

    /**
     * cartOrderDel(删除购物车商品)
     *
     * @Param 
     * @param order
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 11:46:09
     **/
    @RequestMapping("/cartOrderDel")
    public ResponseDto cartOrderDel(@RequestBody Order order){
        return orderBiz.cartOrderDel(order);
    }

}
