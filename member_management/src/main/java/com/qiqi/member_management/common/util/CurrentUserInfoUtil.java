package com.qiqi.member_management.common.util;

import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.management.business.model.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * CurrentUserInfoUtil类简述
 *  获取当前用户工具类
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月15号 15:13
 * @Version 1.0.0
 */
public class CurrentUserInfoUtil {

    /**
     * getCurrentUser(获取当前用户)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.UserInfo
     * @exception 
     * @Date  2019-04-15 15:16:15
     **/
    public static UserInfo getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        if (null == subject || null == subject.getPrincipal()){
            // 表示当前用户未登录
            throw new BizException("300000");
        }
        return (UserInfo) subject.getPrincipal();
    }
}
