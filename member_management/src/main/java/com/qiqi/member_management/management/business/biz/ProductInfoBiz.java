package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.ProductInfoListRequestDto;
import com.qiqi.member_management.management.business.mapper.ProductInfoMapper;
import com.qiqi.member_management.management.business.model.model.extend.ProductInfoExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ProductInfoBiz类简述
 *
 * @Description
 * @Author qiqi
 * @Date 2019年04月17号 19:33
 * @Version 1.0.0
 */
@Service
public class ProductInfoBiz {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * productInfoList(商品列表查询)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-17 19:41:11
     **/
    public ResponseDto productInfoList(ProductInfoListRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        List<ProductInfoExtend> productInfoList = productInfoMapper.productInfoList(requestDto);
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResult(productInfoList);
        return responseDto;
    }
}
