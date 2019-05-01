package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.dto.request.ProductInfoListRequestDto;
import com.qiqi.member_management.management.business.model.ProductInfo;
import com.qiqi.member_management.management.business.model.model.extend.ProductInfoExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer id);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);

    /**
     * productInfoList(查询商品列表)
     *
     * @Param 
     * @param requestDto
     * @return List<ProductInfoExtend>
     * @exception 
     * @Date  2019-04-17 19:43:47
     **/
     List<ProductInfoExtend> productInfoList(ProductInfoListRequestDto requestDto);

     /**
      * queryProductInfoByName(根据商品名称查询商品信息)
      *
      * @Param
      * @param productName
      * @return com.qiqi.member_management.management.business.model.ProductInfo
      * @exception
      * @Date  2019-04-22 08:51:32
      **/
    ProductInfo queryProductInfoByName(@Param("productName") String productName);

    /**
     * getLastProductInfo(获取最新商品信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.ProductInfo
     * @exception 
     * @Date  2019-04-22 09:41:24
     **/
    ProductInfo getLastProductInfo();

    /**
     * saveProductInfo(新增商品信息)
     *
     * @Param 
     * @param productInfo
     * @return void
     * @exception 
     * @Date  2019-04-22 09:41:11
     **/
    void saveProductInfo(ProductInfo productInfo);

    /**
     * deleteProductInfo(作废商品信息)
     *
     * @Param 
     * @param productInfo
     * @return void
     * @exception 
     * @Date  2019-04-22 11:46:10
     **/
    void deleteProductInfo(ProductInfo productInfo);

    /**
     * updateProductInfo(更新商品信息)
     *
     * @Param 
     * @param productInfo
     * @return void
     * @exception 
     * @Date  2019-04-22 19:07:47
     **/
    void updateProductInfo(ProductInfo productInfo);

    /**
     * queryProductInfo(根据productId获取商品信息)
     *
     * @Param 
     * @param productId
     * @return com.qiqi.member_management.management.business.model.ProductInfo
     * @exception 
     * @Date  2019-05-01 10:21:13
     **/
    ProductInfo queryProductInfo(String productId);
}