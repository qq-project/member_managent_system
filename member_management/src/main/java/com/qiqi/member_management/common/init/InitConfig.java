package com.qiqi.member_management.common.init;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * InitConfig类简述
 * 该类是针对所有的初始化类进行统一加载
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 12:43
 * @Version 1.0.0
 */
@Component
public class InitConfig extends InitExceptionProperties implements InitializingBean, DisposableBean {


   
    
    @Override
    public void destroy() throws Exception {
    }

    /**
     * afterPropertiesSet(加载所有初始化配置)
     *
     * @Param
     * @return void
     * @exception
     * @Date  2019-04-06 12:45:24
     **/
    @Override
    public void afterPropertiesSet() throws Exception {
        super.initExceptionProperties();
    }
}
