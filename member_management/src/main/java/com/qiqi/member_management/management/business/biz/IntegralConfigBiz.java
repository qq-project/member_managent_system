package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CodeBuilderUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.IntegralConfigUpdateRequestDto;
import com.qiqi.member_management.management.business.mapper.IntegralConfigMapper;
import com.qiqi.member_management.management.business.model.IntegralConfig;
import com.qiqi.member_management.management.business.vo.IntegralAmountVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * IntegralConfigBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月25号 9:22
 * @Version 1.0.0
 */
@Service
public class IntegralConfigBiz {

    @Autowired
    private IntegralConfigMapper integralConfigMapper;

    private static final Logger logger = LoggerFactory.getLogger(IntegralConfigBiz.class);
    /**
     * queryInfoByIntegral(根据积分确定会员等级和折扣)
     *
     * @Param 
     * @param integral
     * @return com.qiqi.member_management.management.business.model.IntegralConfig
     * @exception 
     * @Date  2019-04-25 09:23:49
     **/
    public IntegralConfig queryInfoByIntegral(Integer integral){
        return integralConfigMapper.queryIntegralConfig(integral);
    }

    /**
     * queryIntegralList(查询所有的积分配置信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 14:35:12
     **/
    public ResponseDto queryIntegralList() {
        List<IntegralConfig> integralConfigList = integralConfigMapper.selectAll();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResult(integralConfigList);
        return responseDto;
    }


    /**
     * updateIntegralConfig(更改积分配置信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 15:20:06
     **/
    @Transactional
    public ResponseDto updateIntegralConfig(IntegralConfigUpdateRequestDto requestDto) {
        List<IntegralConfig> integralConfigList = requestDto.getIntegralConfigList();
        if (CollectionUtils.isEmpty(integralConfigList)){
           logger.error(MsgManagement.getMsg(100051));
           throw new BizException(100051);
        }
        for (IntegralConfig integralConfig : integralConfigList){
            integralConfig.setRate(requestDto.getRate());
            if (integralConfig.getIntegralMaxAmount().compareTo(integralConfig.getIntegralMinAmount()) <= 0){
                logger.error(MsgManagement.getMsg(100051));
                throw new BizException(100051);
            }
            // 批量更新
            integralConfigMapper.updateIntegralConfig(integralConfig);
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }

    /**
     * getIntegralMaxAmount(获取积分配置表中，最大的范围和折扣力度最大)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 15:52:29
     **/
    public ResponseDto getIntegralMaxAmount() {
        IntegralAmountVo integralAmountVo = integralConfigMapper.getIntegralMaxAmount();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResult(integralAmountVo);
        return responseDto;
    }

    /**
     * saveIntegralConfigRequest(新增积分配置)
     *
     * @Param 
     * @param integralConfig
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 16:10:59
     **/
    public ResponseDto saveIntegralConfigRequest(IntegralConfig integralConfig) {
        integralConfig.setIntegralId(CodeBuilderUtil.codeGenerator(integralConfigMapper.getLastIntegralConfig().getId(), Contant.INTEGRAL_ID_LENGTH, Contant.INTEGRAL_ID_PREFIX));
        // 缺少后台校验
        integralConfigMapper.saveIntegralConfigRequest(integralConfig);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }
}
