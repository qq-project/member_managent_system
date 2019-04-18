package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.ProductInfoBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.ProductInfoListRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductInfoController类简述
 * 商品信息控制器
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月17号 19:36
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {

    @Autowired
    private ProductInfoBiz productInfoBiz;

    /**
     * productInfoList(查询商品列表)
     *
     * @Param
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception
     * @Date  2019-04-17 19:40:16
     **/
    @RequestMapping("/productInfoList")
    public ResponseDto productInfoList(@RequestBody ProductInfoListRequestDto requestDto){
        return productInfoBiz.productInfoList(requestDto);
    }
}
