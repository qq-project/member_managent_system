package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.CategoryInfoBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * CategoryInfoController类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月18号 9:37
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/categoryInfo")
public class CategoryInfoController {

    @Autowired
    private CategoryInfoBiz categoryInfoBiz;
    /**
     * categoryInfoList(查询类别列表)
     *
     * @Param 
     * @param params
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-18 09:38:43
     **/
    @RequestMapping("/categoryInfoList")
    public ResponseDto categoryInfoList(@RequestBody Map<String, Object> params){
        return categoryInfoBiz.categoryInfoList(params);
    }

    /**
     * addCategoryInfo(新增类别信息)
     *
     * @Param 
     * @param params
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-18 10:28:09
     **/
    @RequestMapping("/addCategoryInfo")
    public ResponseDto addCategoryInfo(@RequestBody Map<String, Object> params){
        return categoryInfoBiz.addCategoryInfo(params);
    }
}
