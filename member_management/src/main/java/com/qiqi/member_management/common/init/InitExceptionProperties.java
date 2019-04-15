package com.qiqi.member_management.common.init;

import com.qiqi.member_management.common.exception.MsgManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * InitExceptionProperties类简述
 *  初始化 异常配置文件
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 12:29
 * @Version 1.0.0
 */
public class InitExceptionProperties {

    private static Logger logger = LoggerFactory.getLogger(InitExceptionProperties.class);

    public void initExceptionProperties(){
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/exception.properties"));
            MsgManagement.setProperties(properties);
        } catch (IOException e) {
            logger.error("加载异常消息配置文件异常",e);
        }
    }
}
