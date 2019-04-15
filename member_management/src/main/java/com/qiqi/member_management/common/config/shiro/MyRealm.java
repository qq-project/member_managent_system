package com.qiqi.member_management.common.config.shiro;

import com.qiqi.member_management.management.business.biz.UserBiz;
import com.qiqi.member_management.management.business.biz.UserInfoBiz;
import com.qiqi.member_management.management.business.model.User;
import com.qiqi.member_management.management.business.model.UserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MyRealm类简述
 *  认证授权管理，定义一种规则，当用户登录进来时，需要和这里的数据进行对比
 * @Description
 * @Author qiqi
 * @Date 2019年04月06号 14:05
 * @Version 1.0.0
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoBiz userInfoBiz;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    /**
     * doGetAuthenticationInfo(认证配置(登录))
     *
     * @Param
     * @param token 登录令牌，里面存储着用户信息
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @exception
     * @Date  2019-04-06 14:13:45
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 用户名
        String username = (String)token.getPrincipal();
        // 密码
        String password = new String((char[]) token.getCredentials());
        // 根据用户名查询用户信息
        UserInfo user = userInfoBiz.queryUserInfoByEmail(username);
        // 校验登录
        if (null == user){
            logger.error("用户名或密码错误！");
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())){
            logger.error("用户名或密码错误！");
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
