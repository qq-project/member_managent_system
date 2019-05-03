package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.SaleInfoBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SaleInfoController类简述
 *
 * @Description 销售情况展示
 * @Author shen.chu1
 * @Date 2019年05月03号 12:07
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/saleInfo")
public class SaleInfoController {

    @Autowired
    private SaleInfoBiz saleInfoBiz;
    /**
     * saleInfoProduct(商品销售情况)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-03 12:08:37
     **/
    @RequestMapping("/saleInfoProduct")
    public ResponseDto saleInfoProduct(){
       return saleInfoBiz.saleInfoProduct();
    }
    
    /**
     * orderInfoProduct(销售订单图表展示)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-03 13:44:56
     **/
    @RequestMapping("/orderInfoProduct")
    public ResponseDto orderInfoProduct(){
        return saleInfoBiz.orderInfoProduct();
    }
            
    /**
     * orderInfoDay(每日销量图)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-03 14:53:15
     **/
    @RequestMapping("/orderInfoDay")
    public ResponseDto orderInfoDay(){
        return saleInfoBiz.orderInfoDay();
    }
}
