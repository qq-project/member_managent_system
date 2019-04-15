package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * LoggerTestController类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 11:25
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/logger")
public class LoggerTestController {

    @RequestMapping("/test")
    public String test(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return user.toString();
    }
}
