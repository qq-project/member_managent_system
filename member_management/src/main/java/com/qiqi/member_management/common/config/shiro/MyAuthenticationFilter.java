package com.qiqi.member_management.common.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyAuthenticationFilter类简述
 *  重写shiro的UserFilter，实现通过OPTIONS请求
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月10号 19:02
 * @Version 1.0.0
 */
public class MyAuthenticationFilter extends UserFilter {


    /**
     * preHandle(检测OPTION请求)
     *  在访问过来的时候检测是否为OPTIONS请求，如果是就直接返回true
     * @Param 
     * @param request
     * @param response
     * @return boolean
     * @exception 
     * @Date  2019-04-10 19:06:38
     **/
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        // 当请求未OPTIONS时，直接放行
        if (RequestMethod.OPTIONS.name().equals(httpServletRequest.getMethod())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return true;
        }
        return super.preHandle(request, response);
    }
    

    private void setHeader(HttpServletRequest request, HttpServletResponse response){
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        //response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }
}
