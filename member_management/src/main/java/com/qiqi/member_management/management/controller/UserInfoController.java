package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.UserInfoBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.UserListRequestDto;
import com.qiqi.member_management.management.business.dto.request.UserModifiedRequestDto;
import com.qiqi.member_management.management.business.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserInfoController类简述
 *
 * @Description 用户管理中心
 * @Author shen.chu1
 * @Date 2019年05月01号 17:15
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    /**
     * (查询用户列表)
     *
     * @Param 
     * @param null
     * @return 
     * @exception 
     * @Date  2019-05-01 17:18:06
     **/
    @Autowired
    private UserInfoBiz userInfoBiz;
    
    /**
     * userList(查询用户列表)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-02 10:25:35
     **/
    @RequestMapping("/userList")
    public ResponseDto userList(UserListRequestDto requestDto){
        return userInfoBiz.userList(requestDto);
    }

    /**
     * userDetail(查询用户详情)
     *
     * @Param 
     * @param userInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-02 10:26:12
     **/
    @RequestMapping("/userDetail")
    public ResponseDto userDetail(@RequestBody UserInfo userInfo){
        return userInfoBiz.userDetail(userInfo);
    }

    /**
     * updateUserInfo(修改用户信息)
     *
     * @Param 
     * @param userInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-02 10:56:50
     **/
    @RequestMapping("/updateUserInfo")
    public ResponseDto updateUserInfo(@RequestBody UserInfo userInfo){
        return userInfoBiz.updateUserInfo(userInfo);
    }

    /**
     * delUserInfo(删除用户信息)
     *
     * @Param 
     * @param userInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-02 11:40:21
     **/
    @RequestMapping("/userDel")
    public ResponseDto delUserInfo(@RequestBody UserInfo userInfo){
        return userInfoBiz.delUserInfo(userInfo);
    }
}
