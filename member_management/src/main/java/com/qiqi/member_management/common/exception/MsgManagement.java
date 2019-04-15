package com.qiqi.member_management.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * MsgManagement类简述
 * 消息管理器
 *  对异常消息进行统一处理
 * @Description
 * @Author qiqi
 * @Date 2019年04月06号 12:10
 * @Version 1.0.0
 */
public class MsgManagement {

    /* 日志对象*/
    private static final Logger logger = LoggerFactory.getLogger(MsgManagement.class);
    /** 读取配置信息*/
    private static Properties messages = new Properties();

    /**
     * setProperties(设置配置文件)
     *
     * @Param
     * @param properties
     * @return void
     * @exception
     * @Date  2019-04-06 12:32:15
     **/
    public static void setProperties(Properties properties){
        messages = properties ;
    }
    /**
     * encoding(消息转码器)
     *
     * @Param
     * @param msg
     * @return java.lang.String
     * @exception
     * @Date  2019-04-06 12:13:16
     **/
    private static String encoding(String msg){
        try{

            if (null == msg){
                return "";
            }
            // 将默认的ISO-8859-1 转换成 UTF-8
            msg = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
        } catch(Exception e){
            logger.error("msg: "+msg+ "转码异常");
            return "";
        }
        return msg;
    }

    /**
     * getMsg(根据Code获取对应的msg)
     *
     * @Param
     * @param code
     * @return java.lang.String
     * @exception
     * @Date  2019-04-06 12:16:33
     **/
    public static String getMsg(Object code){
        String msg = "";
        try{
            // 读取消息
            msg = messages.getProperty(String.valueOf(code));
            // 消息转码
            msg = encoding(msg);
        }catch(Exception e){
            logger.error("MsgManager getMsg 异常："+e);;
        }
        return msg;
    }

    /**
     * getMsg(根据输入code，拼接msg 占位符)
     *  格式：100028=查询对账交易流水详情失败，%s
     * @Param
     * @param code
     * @param msg
     * @return java.lang.String
     * @exception
     * @Date  2019-04-06 12:19:20
     **/
    public static String getMsg(Object code,String ... msg){
        String msgConfig = messages.getProperty(String.valueOf(code));
        msgConfig = encoding(msgConfig);
        if (StringUtils.isBlank(msgConfig)){
            if (null != msg){
                return StringUtils.join(msg);
            } else {
                return "";
            }
        } else {
            for (int i = 0; i < msg.length; i++){
                msgConfig = msgConfig.replaceFirst("%s", String.valueOf(msg[i]));
            }
            return msgConfig;
        }
    }
    
}
