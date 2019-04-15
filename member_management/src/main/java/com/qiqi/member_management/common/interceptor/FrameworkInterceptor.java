package com.qiqi.member_management.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FrameworkInterceptor类简述
 *  系统访问拦截器
 * @Description
 * @Author qiqi
 * @Date 2019年04月06号 11:51
 * @Version 1.0.0
 */
public class FrameworkInterceptor implements HandlerInterceptor{

    /** 打印日志*/
    private static final Logger logger = LoggerFactory.getLogger(FrameworkInterceptor.class);

    /**
     * preHandle(访问之前拦截请求)
     *
     * @Param
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @exception
     * @Date  2019-04-06 11:55:29
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("进入FrameworkInterceptor拦截器preHandle方法，当前线程：" +Thread.currentThread() + "时间：" +
                System.currentTimeMillis());
        logger.info("请求url路径为：" +request.getRequestURL());
        // 这里可以进行登录校验
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        
    }

    /**
     * afterCompletion(请求响应之后 渲染视图之后)
     *
     * @Param
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return void
     * @exception
     * @Date  2019-04-06 11:58:24
     **/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("进入FrameworkInterceptor拦截器afterCompletion方法,当前线程：" + Thread.currentThread() + ",时间："
                + System.currentTimeMillis());
    }
}
