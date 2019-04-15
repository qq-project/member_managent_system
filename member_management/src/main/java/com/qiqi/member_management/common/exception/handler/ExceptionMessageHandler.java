package com.qiqi.member_management.common.exception.handler;

import com.qiqi.member_management.common.exception.BaseException;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.LoginException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ExceptionHandler类简述
 * 统一异常处理类(@ControllerAdvice + @ExceptionHandler 使用)
 * @Description
 * @Author qiqi
 * @Date 2019年04月06号 12:51
 * @Version 1.0.0
 */
@ControllerAdvice
public class ExceptionMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionMessageHandler.class);

    /**
     * handleException(统一异常处理，返回ResponseDto)
     * 拦截所有的异常 Exception
     * @Param 
     * @param e
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-06 12:57:06
     **/
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseDto handleException(Exception e){
        logger.error(ResponseDto.ERROR, e);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.ERROR);
        responseDto.setResMsg("系统异常：" +e.getMessage());
        return responseDto;
    }

    /**
     * handleBizException(BizException统一处理)
     *
     * @Param
     * @param e
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception
     * @Date  2019-04-06 12:59:12
     **/
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResponseDto handleBizException(BizException e){
        logger.info("BizException", e.toString());
        return getResponseDto(e);
    }

    
    /**
     * handleLoginException(登录异常处理)
     *
     * @Param 
     * @param e
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-10 11:20:53
     **/
    @ExceptionHandler({UnknownAccountException.class,IncorrectCredentialsException.class,LockedAccountException.class})
    @ResponseBody
    public ResponseDto handleLoginException(Exception e){
        ResponseDto responseDto = new ResponseDto();
        if (e instanceof UnknownAccountException)  {
            responseDto.setResCode("100001");
            responseDto.setResMsg(MsgManagement.getMsg(100001));
            logger.info("UnknownAccountException", e.toString());
            return responseDto;
        }
        if(e instanceof IncorrectCredentialsException) {
            responseDto.setResCode("100001");
            responseDto.setResMsg(MsgManagement.getMsg(100001));
            logger.info("IncorrectCredentialsException", e.toString());
            return responseDto;
        }
        if (e instanceof LockedAccountException)  {
            responseDto.setResCode("100002");
            responseDto.setResMsg(MsgManagement.getMsg(100002));
            logger.info("LockedAccountException", e.toString());
            return responseDto;
        }
        responseDto.setResCode(ResponseDto.ERROR);
        responseDto.setResMsg("系统异常：" +e.getMessage());
        return responseDto;
    }


    
    /**
     * responseDto(异常返回信息封装)
     *
     * @Param
     * @param e
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception
     * @Date  2019-04-10 11:20:14
     **/
    public ResponseDto getResponseDto(BaseException e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(e.getCode());
        responseDto.setResMsg(e.getMsg());
        return responseDto;
    }
}
