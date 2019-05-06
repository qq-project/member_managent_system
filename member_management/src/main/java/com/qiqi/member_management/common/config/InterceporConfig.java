package com.qiqi.member_management.common.config;

import com.qiqi.member_management.common.interceptor.FrameworkInterceptor;
import com.qiqi.member_management.common.interceptor.ProcessInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.NotNull;

/**
 * InterceporConfig类简述
 *  拦截器配置 需要实现 WebMvcConfigurer
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 11:59
 * @Version 1.0.0
 */
@Configuration
public class InterceporConfig implements WebMvcConfigurer {

    @Bean
    public FrameworkInterceptor frameworkInterceptor(){
        return new FrameworkInterceptor();
    }
    @Bean
    public ProcessInterceptor processInterceptor(){
        return new ProcessInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
         *  配置拦截规则  addPathPatterns
         *  静态文件不拦截   excludePathPatterns
         *  针对前后端分离，好像这个就没啥用啦
         */
        registry.addInterceptor(frameworkInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/statics/img/**")
                .excludePathPatterns("/statics/js/**")
                .excludePathPatterns("/statics/css/**")
                .excludePathPatterns("/statics/fonts/**")
                .excludePathPatterns("/statics/bootstrap/**");
        registry.addInterceptor(processInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/statics/img/**")
                .excludePathPatterns("/statics/js/**")
                .excludePathPatterns("/statics/css/**")
                .excludePathPatterns("/statics/fonts/**")
                .excludePathPatterns("/statics/bootstrap/**");
    }
}
