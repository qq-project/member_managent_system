package com.qiqi.member_management.common.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.management.business.dto.request.SmsRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SendSmsUtil类简述
 *
 * @Description 发送短信工具类
 * @Author shen.chu1
 * @Date 2019年05月02号 13:21
 * @Version 1.0.0
 */
public class SendSmsUtil {

    private static final Logger logger = LoggerFactory.getLogger(SendSmsUtil.class);
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     **/
    static final String PRODUCT = "Dysmsapi";

    /**
     * 产品域名,开发者无需替换
     **/
    static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * 区域
     **/
    static final String REGION_ID = "cn-hangzhou";

    /**
     * 区域
     **/
    static final String END_POINT_NAME = "cn-hangzhou";


    public static SendSmsResponse sendSms(SmsRequestDto requestDto){
        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化acsClient
        IClientProfile profile = DefaultProfile.getProfile(REGION_ID, Contant.ALIYUN_OSS_ACCESS_KEY_ID, Contant.ALIYUN_OSS_ACCESS_KEY_SECRET);
        IAcsClient acsClient = null;
        SendSmsRequest request = null;
        SendSmsResponse sendSmsResponse = null;
        try {
            DefaultProfile.addEndpoint(END_POINT_NAME, REGION_ID, PRODUCT, DOMAIN);
            acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            request = new SendSmsRequest();
            // 发送手机号
            request.setPhoneNumbers(requestDto.getMobile());
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(requestDto.getSignName());
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(requestDto.getTemplateCode());
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"name\":\""+requestDto.getUsername()+"\", \"code\":\""+requestDto.getCode()+"\"}");
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            logger.error(MsgManagement.getMsg(100007));
            throw new BizException(100007);
        }
        return sendSmsResponse;
    }


    /**
     * querySendDetails(根据业务ID查询短信发送记录)
     *
     * @Param 
     * @param requestDto
     * @return com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse
     * @exception 
     * @Date  2019-05-02 13:48:22
     **/
    public static QuerySendDetailsResponse querySendDetails(SmsRequestDto requestDto){
        //可自助调整超时时间
        // 设置超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化acsClient
        IClientProfile profile = DefaultProfile.getProfile(REGION_ID, Contant.ALIYUN_OSS_ACCESS_KEY_ID, Contant.ALIYUN_OSS_ACCESS_KEY_SECRET);
        IAcsClient acsClient = null;
        //组装请求对象
        QuerySendDetailsRequest request = null;
        QuerySendDetailsResponse querySendDetailsResponse = null;
        try {
            DefaultProfile.addEndpoint(END_POINT_NAME, REGION_ID, PRODUCT, DOMAIN);
            acsClient = new DefaultAcsClient(profile);
            request = new QuerySendDetailsRequest();
            //必填-号码
            request.setPhoneNumber(requestDto.getMobile());
            //可选-流水号
            request.setBizId(requestDto.getBizId());
            //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
            SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
            request.setSendDate(ft.format(requestDto.getSendDate()));
            //必填-页大小
            request.setPageSize(10L);
            //必填-当前页码从1开始计数
            request.setCurrentPage(1L);

            //hint 此处可能会抛出异常，注意catch
            querySendDetailsResponse = acsClient.getAcsResponse(request);

        } catch (Exception e) {
            logger.error(MsgManagement.getMsg(100008));
            throw new BizException(100008);
        }
        return null;
    }
}
