package com.qiqi.member_management.common.config.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * CustomerSessionManager类简述
 *
 * @Description
 * 当决定前端与后端代码分开部署时，发现shiro自带的session不起作用了。
 * 然后通过对请求head的分析，然后在网上查找一部分解决方案。
 * 最终就是，登录成功之后，前端接收到后端传回来的sessionId，存入cookie当中。
 * 之后，前端向后端发送请求时，请求Head中都会带上这个sessionid。
 * 后端代码通过对这个sessionid的解析，拿到正确的session。
 * 
 * @Author shen.chu1
 * @Date 2019年04月10号 16:48
 * @Version 1.0.0
 */
public class CustomerSessionManager extends DefaultWebSessionManager {
    /**
     * 获取请求头中key为“Authorization”的value == sessionId
     */
    private static final String AUTHORIZATION ="Authorization";
    private static final String REFERENCED_SESSION_ID_SOURCE = "cookie";

    public CustomerSessionManager(){
        super();
        // 设置session过期时间 一天
        setGlobalSessionTimeout(DEFAULT_GLOBAL_SESSION_TIMEOUT * 48);
    }
    
    /**
     * getSessionId(重写获取SessionId值)
     *  可自定义session获取规则。这里采用ajax请求头 {AUTHORIZATION}携带sessionId的方式
     * @Param
     * @param request
     * @param response
     * @return java.io.Serializable
     * @exception
     * @Date  2019-04-10 16:49:53
     **/
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (StringUtils.isNotBlank(sessionId)){
            // sessionId不为空
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        } else {
            // 否则按照默认规则从cookie中取sessionId
            return super.getSessionId(request, response);
        }
    }
}
