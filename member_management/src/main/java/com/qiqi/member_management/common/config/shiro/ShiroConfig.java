package com.qiqi.member_management.common.config.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig类简述
 * apache shiro 配置类(登录管理)
 * @Description
 *  因为集成 JWT 所以要关闭shiro的 session控制 原因是 在前后端分离项目中，前端是无法获取后端session的
 * @Author qiqi
 * @Date 2019年04月06号 14:05
 * @Version 1.0.0
 */
@Configuration
@ConditionalOnWebApplication
public class ShiroConfig {

    /**
     * shiroFilterFactoryBean(配置shiro过滤工厂，主要是过滤规则定制)
     *
     * @Param 
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @exception 
     * @Date  2019-04-06 14:07:10
     *  modified 不要
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 配置 安全管理器(这是管理所有shiro功能)
        bean.setSecurityManager(securityManager());
        // 定义拦截规则
        bean.setUnauthorizedUrl("/403");
        Map<String, Filter> filters = new HashMap<>();
        filters.put("user",new MyAuthenticationFilter());
        bean.setFilters(filters);
        // 拦截map 需要注意的是filterChain基于短路机制，即最先匹配原则
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 定义filterChain 静态资源不拦截
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        // druid 数据源拦截页面不拦截
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/register/userRegister", "anon");
        filterChainDefinitionMap.put("/**","user");
        filterChainDefinitionMap.put("/**","authc");
        // 配置退出过滤器， 其中具体的退出代码shiro已经帮助我们实现了
        filterChainDefinitionMap.put("/user/logout", "logout");
        bean.setLoginUrl("/user/unauth");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    /**
     * myRealm(自定义 realm)
     *
     * @Param
     * @return com.qiqi.member_management.common.config.shiro.MyRealm
     * @exception
     * @Date  2019-04-06 14:13:03
     **/
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

    /**
     * rememberMeCookie(remember me选项 自定义cookie)
     *  登录时，只需要将请求参数 rememberMe(true/false) 传递过来即可
     * @Param
     * @return org.apache.shiro.web.servlet.SimpleCookie
     * @exception
     * @Date  2019-04-06 14:46:27
     **/
    @Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 保留时间 一天
        simpleCookie.setMaxAge(86400);
        return simpleCookie;
    }

    /**
     * cookieRememberMeManager(cookie 管理对象)
     *
     * @Param
     * @return org.apache.shiro.web.mgt.CookieRememberMeManager
     * @exception
     * @Date  2019-04-06 14:47:32
     **/
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        // rememberMe cookie 加密的秘钥
        manager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return manager;
    }

    /**
     * securityManager(shiro管理器)
     * 所有的拓展都需要在shiro管理器中进行配置
     * @Param
     * @return org.apache.shiro.mgt.SecurityManager
     * @exception
     * @Date  2019-04-06 14:09:56
     **/
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义realm
        securityManager.setRealm(myRealm());
        // 将cookieManage交给 安全管理器
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    /**
     * sessionManager(请输入描述)
     *
     * @Param
     * @return org.apache.shiro.session.mgt.SessionManager
     * @exception
     * @Date  2019-04-10 16:54:55
     **/
    @Bean
    public SessionManager sessionManager(){
        CustomerSessionManager manager = new CustomerSessionManager();
        /*使用了shiro自带缓存，
        如果设置 redis为缓存需要重写CacheManager（其中需要重写Cache）
        manager.setCacheManager(this.RedisCacheManager());*/
        manager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return manager;
    }

}
