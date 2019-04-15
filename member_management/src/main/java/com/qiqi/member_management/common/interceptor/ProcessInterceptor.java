package com.qiqi.member_management.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ProcessInterceptor类简述
 * axios请求跨域问题，拦截OPTION请求
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月10号 17:38
 * @Version 1.0.0
 */
public class ProcessInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ProcessInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        // 携带认证信息
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String method = request.getMethod();
        logger.info("进入 ProcessInterceptor preHandle方法，请求方式为：" +method);
        if (RequestMethod.OPTIONS.name().equals(method)){
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        
    }
}
