package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer id);

    List<ProductCategory> selectAll();

    int updateByPrimaryKey(ProductCategory record);

    /**
     * saveProductCategory(批量信息商品类别信息)
     *
     * @Param 
     * @param productCategory
     * @return void
     * @exception 
     * @Date  2019-04-22 09:52:12
     **/
    void saveProductCategory(ProductCategory productCategory);

    /**
     * queryProductCategoryLists(根据条件查询商品类型关联关系)
     *
     * @Param 
     * @param productCategory
     * @return java.util.List<com.qiqi.member_management.management.business.model.ProductCategory>
     * @exception 
     * @Date  2019-04-22 12:44:13
     **/
    List<ProductCategory> queryProductCategoryLists(ProductCategory productCategory);

    /**
     * delStorageInfo(作废商品类别信息)
     *
     * @Param 
     * @param productCategoryDel
     * @return void
     * @exception 
     * @Date  2019-04-22 13:00:28
     **/
    void delStorageInfo(ProductCategory productCategoryDel);
}