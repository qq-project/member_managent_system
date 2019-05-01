package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.IntegralConfig;
import com.qiqi.member_management.management.business.vo.IntegralAmountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntegralConfig record);

    IntegralConfig selectByPrimaryKey(Integer id);

    List<IntegralConfig> selectAll();

    int updateByPrimaryKey(IntegralConfig record);

    /**
     * queryIntegralConfig(根据积分确定用户积分等级)
     *
     * @Param 
     * @param integral
     * @return com.qiqi.member_management.management.business.model.IntegralConfig
     * @exception 
     * @Date  2019-04-25 09:24:38
     **/
    IntegralConfig queryIntegralConfig(@Param("integral") Integer integral);

    /**
     * updateIntegralConfig(更新积分配置信息)
     *
     * @Param 
     * @param integralConfig
     * @return void
     * @exception 
     * @Date  2019-05-01 15:23:05
     **/
    void updateIntegralConfig(IntegralConfig integralConfig);

    /**
     * getIntegralMaxAmount(请输入描述)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.vo.IntegralAmountVo
     * @exception 
     * @Date  2019-05-01 15:55:34
     **/
    IntegralAmountVo getIntegralMaxAmount();

    /**
     * saveIntegralConfigRequest(新增积分配置信息)
     *
     * @Param 
     * @param integralConfig
     * @return void
     * @exception 
     * @Date  2019-05-01 16:11:51
     **/
    void saveIntegralConfigRequest(IntegralConfig integralConfig);
    
    /**
     * getLastIntegralConfig(查询最新的积分配置信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.IntegralConfig
     * @exception 
     * @Date  2019-05-01 16:26:39
     **/
    IntegralConfig getLastIntegralConfig();
}