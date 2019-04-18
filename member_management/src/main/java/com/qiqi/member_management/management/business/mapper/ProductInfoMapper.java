package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.dto.request.ProductInfoListRequestDto;
import com.qiqi.member_management.management.business.model.ProductInfo;
import com.qiqi.member_management.management.business.model.model.extend.ProductInfoExtend;

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
}