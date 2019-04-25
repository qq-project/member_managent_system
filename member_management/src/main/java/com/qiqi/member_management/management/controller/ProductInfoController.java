package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.ProductInfoBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.ProductInfoListRequestDto;
import com.qiqi.member_management.management.business.dto.request.ProductInfoSaveRequestDto;
import com.qiqi.member_management.management.business.dto.request.ProductInfoUpdateRequestDto;
import com.qiqi.member_management.management.business.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    /**
     * saveProductInfo(新增商品信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-19 19:30:09
     **/
    @RequestMapping("/saveProductInfo")
    public ResponseDto saveProductInfo(@RequestBody ProductInfoSaveRequestDto requestDto){
        return productInfoBiz.saveProductInfo(requestDto);
    }

    /**
     * deleteProductInfo(删除商品信息)
     *
     * @Param 
     * @param productInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-22 11:43:39
     **/
    @RequestMapping("/deleteProductInfo")
    public ResponseDto deleteProductInfo(@RequestBody ProductInfo productInfo){
        return productInfoBiz.deleteProductInfo(productInfo);
    }

    /**
     * updateProductInfo(修改商品信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-22 15:01:28
     **/
    @RequestMapping("/updateProductInfo")
    public ResponseDto updateProductInfo(@RequestBody ProductInfoUpdateRequestDto requestDto){
        return productInfoBiz.updateProductInfo(requestDto);
    }
}
