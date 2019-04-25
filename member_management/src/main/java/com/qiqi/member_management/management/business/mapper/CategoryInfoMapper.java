package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.CategoryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CategoryInfo record);

    CategoryInfo selectByPrimaryKey(Integer id);

    List<CategoryInfo> selectAll();

    int updateByPrimaryKey(CategoryInfo record);

    List<CategoryInfo> categoryInfoList(@Param("map") Map<String,Object> params);
    
    /**
     * queryCategoryInfoByName(根据名称查询最新一条类别信息)
     *
     * @Param 
     * @param params
     * @return com.qiqi.member_management.management.business.model.CategoryInfo
     * @exception 
     * @Date  2019-04-18 10:43:26
     **/
    CategoryInfo queryCategoryInfoByName(@Param("map") Map<String,Object> params);

    /**
     * getLastCategoryInfo(获取最新一条数据)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.CategoryInfo
     * @exception 
     * @Date  2019-04-18 10:51:27
     **/
    CategoryInfo getLastCategoryInfo();

    /**
     * addCategoryInfo(新增类别信息)
     *
     * @Param 
     * @param categoryInfo
     * @return void
     * @exception 
     * @Date  2019-04-18 11:05:19
     **/
    void addCategoryInfo(CategoryInfo categoryInfo);

    /**
     * queryCategoryListByProductId(查询类列表)
     *
     * @Param 
     * @param productId
     * @return java.util.List<com.qiqi.member_management.management.business.model.CategoryInfo>
     * @exception 
     * @Date  2019-04-22 10:41:08
     **/
    List<CategoryInfo> queryCategoryListByProductId(@Param("productId") String productId);
}