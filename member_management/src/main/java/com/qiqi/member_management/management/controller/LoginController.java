package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.MD5Util;
import com.qiqi.member_management.management.business.biz.UserInfoBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.UserLoginRequestDto;
import com.qiqi.member_management.management.business.dto.request.UserModifiedRequestDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * LoginController类简述
 *  登录控制器
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 14:18
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserInfoBiz userInfoBiz;
    /**
     * login(登录验证)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-06 14:21:12
     **/
    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserLoginRequestDto requestDto){
        ResponseDto responseDto = new ResponseDto();
        // 密码加密
        String password = MD5Util.encrypt(requestDto.getUsername(), requestDto.getPassword());
        // 获取认证令牌   可以选择记住我
        UsernamePasswordToken token = new UsernamePasswordToken(requestDto.getUsername(), password, requestDto.isRememberMe());
        // 获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        //   此时就会去realm中认证，认证失败时抛出异常
        subject.login(token);
        responseDto.setResMsg("登录成功");
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResult(subject.getPrincipal());
        return responseDto;
    }

    /**
     * queryUserInfo(查询当前登录用户信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-15 09:07:20
     **/
    @RequestMapping("/queryUserInfo")
    public ResponseDto queryUserInfo(){
        return userInfoBiz.queryUserInfo();
    }


    /**
     * saveUserInfoRequest(更新当前用户信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-16 11:31:50
     **/
    @RequestMapping("/saveUserInfo")
    public ResponseDto saveUserInfo(@RequestBody UserModifiedRequestDto requestDto){
        String password = requestDto.getPassword();
        ResponseDto responseDto = userInfoBiz.saveUserInfo(requestDto);
        responseDto.setResCode(ResponseDto.SUCCESS);
        // 用户修改密码，后台强制进行退出登录操作
        if (StringUtils.isNotBlank(password)){
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }
        return responseDto;
    }
    
    /**
     * unauth(未登录跳转)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-10 19:37:42
     **/
    @RequestMapping("/unauth")
    public ResponseDto unauth(){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode("300000");
        responseDto.setResMsg(MsgManagement.getMsg(300000));
        return responseDto;
    }

    /**
     * logout(退出登录)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-13 12:39:57
     **/
    @RequestMapping("/logout")
    public ResponseDto logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResMsg(MsgManagement.getMsg("退出成功"));
        return responseDto;
    }
}
