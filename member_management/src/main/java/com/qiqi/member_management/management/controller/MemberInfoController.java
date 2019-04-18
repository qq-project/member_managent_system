package com.qiqi.member_management.management.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiqi.member_management.management.business.biz.MemberInfoBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.MemberInfoSaveRequestDto;
import com.qiqi.member_management.management.business.model.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MemberInfoController类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月10号 17:17
 * @Version 1.0.0
 */
@RestController
@RequestMapping("memberInfo")
public class MemberInfoController {

    @Autowired
    private MemberInfoBiz memberInfoBiz;
    /**
     * memberInfoList(查询所有用户)
     *
     * @Param
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception
     * @Date  2019-04-10 17:18:24
     **/
    @RequestMapping("/memberInfoList")
    public ResponseDto memberInfoList(@RequestBody MemberInfo memberInfo){
       ResponseDto responseDto = new ResponseDto();
       responseDto.setResCode(ResponseDto.SUCCESS);
       // 转换成JSON
       responseDto.setResult(JSONObject.toJSON(memberInfoBiz.memberInfoList(memberInfo)));
       return responseDto;
    }

    /**
     * updateMemberInfo(更新会员信息)
     *
     * @Param 
     * @param memberInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-17 08:39:06
     **/
    @RequestMapping("/updateMemberInfo")
    public ResponseDto updateMemberInfo(@RequestBody MemberInfo memberInfo){
        return memberInfoBiz.updateMemberInfo(memberInfo);
    }

    /**
     * saveMemberInfo(保存会员信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-17 10:22:13
     **/
    @RequestMapping("/saveMemberInfo")
    public ResponseDto saveMemberInfo(@RequestBody @Validated MemberInfoSaveRequestDto requestDto){
        return memberInfoBiz.saveMemberInfo(requestDto);
    }

    /**
     * deleteMemberInfo(根据ID作废会员信息)
     *
     * @Param 
     * @param id
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-17 13:30:53
     **/
    @RequestMapping("/deleteMemberInfo")
    public ResponseDto deleteMemberInfo(@RequestBody Integer id){
        return memberInfoBiz.deleteMemberInfo(id);
    }
}
