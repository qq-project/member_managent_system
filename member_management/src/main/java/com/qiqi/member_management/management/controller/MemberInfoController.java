package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.dto.ResponseDto;
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

    /**
     * memberInfoList(查询所有用户)
     *
     * @Param
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception
     * @Date  2019-04-10 17:18:24
     **/
    @RequestMapping("/memberInfoList")
    public ResponseDto memberInfoList(){
       ResponseDto responseDto = new ResponseDto();
       responseDto.setResCode(ResponseDto.SUCCESS);
       return responseDto;
    }
}
