package com.qiqi.member_management.common.util;

import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateConvertUtil类简述
 * 日期转换
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月17号 11:14
 * @Version 1.0.0
 */
public class DateConvertUtil {

    private static final String yyyyMMDDPattern = "yyyy-MM-dd";

    private static final String yyyyMMDDHHMMSSPattern = "yyyy-MM-dd HH:mm:ss";
    private static final Logger logger = LoggerFactory.getLogger(DateConvertUtil.class);
    /**
     * convertStringToDate(yyyy-MM-dd)
     *
     * @Param
     * @param dateStr
     * @return java.util.Date
     * @exception
     * @Date  2019-04-17 11:15:11
     **/
    public static Date convertStringToDate(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMDDPattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
            return date;
        } catch (Exception e) {
            logger.error(MsgManagement.getMsg(200001));
            throw new BizException(200001);
        }
    }

    /**
     * convertDateToString(将日期转换成字符串)
     *
     * @Param 
     * @param date
     * @return java.util.Date
     * @exception 
     * @Date  2019-05-01 13:08:27
     **/
    public static String convertDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMDDHHMMSSPattern);
        String dateStr = "";
        try {
            dateStr = sdf.format(date);
            return dateStr;
        } catch (Exception e) {
            logger.error(MsgManagement.getMsg(200001));
            throw new BizException(200001);
        }
    }
}
