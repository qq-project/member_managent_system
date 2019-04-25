package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.management.business.mapper.OrderRelMapper;
import com.qiqi.member_management.management.business.model.OrderRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderRelBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月25号 8:17
 * @Version 1.0.0
 */
@Service
public class OrderRelBiz {

    @Autowired
    private OrderRelMapper orderRelMapper;

    /**
     * getLastOrderRel(获取最新订单关联信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.OrderRel
     * @exception 
     * @Date  2019-04-25 09:55:42
     **/
    public OrderRel getLastOrderRel(){
        return orderRelMapper.getLastOrderRel();
    }

    /**
     * saveOrderRel(新增订单关联信息)
     *
     * @Param 
     * @param orderRel
     * @return void
     * @exception 
     * @Date  2019-04-25 10:01:50
     **/
    public void saveOrderRel(OrderRel orderRel){
        orderRel.setAddedByName(Contant.ADDED_NAME);
        orderRel.setLastModifiedByName(Contant.ADDED_NAME);
        orderRelMapper.saveOrderRel(orderRel);
    }
}
