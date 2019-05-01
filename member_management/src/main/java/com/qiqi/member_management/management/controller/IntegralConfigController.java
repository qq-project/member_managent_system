package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.IntegralConfigBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.IntegralConfigUpdateRequestDto;
import com.qiqi.member_management.management.business.model.IntegralConfig;
import com.qiqi.member_management.management.business.vo.IntegralAmountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IntegralConfigController类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年05月01号 14:33
 * @Version 1.0.0
 */
@RequestMapping("/integralConfig")
@RestController
public class IntegralConfigController {

    @Autowired
    private IntegralConfigBiz integralConfigBiz;
    /**
     * queryIntegralList(查询所有的积分配置信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 14:34:24
     **/
    @RequestMapping("/queryIntegralList")
    public ResponseDto queryIntegralList(){
        return integralConfigBiz.queryIntegralList();
    }

    /**
     * updateIntegralConfig(更改积分配置信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 15:19:54
     **/
    @RequestMapping("/updateIntegralConfig")
    public ResponseDto updateIntegralConfig(@RequestBody IntegralConfigUpdateRequestDto requestDto){
        return integralConfigBiz.updateIntegralConfig(requestDto);
    }

    /**
     * getIntegralMaxAmount(查询当前积分配置表中最大的数据)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 15:57:58
     **/
    @RequestMapping("/getIntegralMaxAmount")
    public ResponseDto getIntegralMaxAmount(){
        return integralConfigBiz.getIntegralMaxAmount();
    }

    /**
     * saveIntegralConfigRequest(新增)
     *
     * @Param 
     * @param integralConfig
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 16:10:49
     **/
    @RequestMapping("/saveIntegralConfig")
    public ResponseDto saveIntegralConfigRequest(@RequestBody IntegralConfig integralConfig){
        return  integralConfigBiz.saveIntegralConfigRequest(integralConfig);
    }
}
