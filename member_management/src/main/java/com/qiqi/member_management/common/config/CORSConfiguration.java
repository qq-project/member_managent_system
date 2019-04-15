package com.qiqi.member_management.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORSConfiguration类简述
 * springboot 解决跨域问题
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月10号 19:16
 * @Version 1.0.0
 */
@Configuration
public class CORSConfiguration implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*")
                .allowedHeaders("*")
                // 设置是否允许跨域传cookie
                .allowCredentials(true)
                // 设置缓存时间，减少重复响应
                .maxAge(3600);
    }
}
