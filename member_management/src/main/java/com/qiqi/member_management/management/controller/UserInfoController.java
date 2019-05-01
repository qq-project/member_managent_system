package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.UserInfoBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.UserListRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/userList")
    public ResponseDto userList(UserListRequestDto requestDto){
        return userInfoBiz.userList(requestDto);
    }
}
