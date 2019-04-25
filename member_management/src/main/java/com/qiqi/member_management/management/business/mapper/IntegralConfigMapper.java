package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.IntegralConfig;
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
}