package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.UserInfoBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.UserRegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RegisterController类简述
 * 注册控制器
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 15:19
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserInfoBiz userInfoBiz;
    /**
     * register(用户注册)
     *
     * @Param
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception
     * @Date  2019-04-13 12:33:53
     **/
    @RequestMapping("/userRegister")
    public ResponseDto register(@RequestBody @Validated UserRegisterRequestDto requestDto){
      return userInfoBiz.register(requestDto);
    }
}
