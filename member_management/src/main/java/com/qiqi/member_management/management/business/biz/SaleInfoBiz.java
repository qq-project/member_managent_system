package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.vo.OrderDayVo;
import com.qiqi.member_management.management.business.vo.OrderInfoDaysVo;
import com.qiqi.member_management.management.business.vo.ProductTotalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SaleInfoBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年05月03号 12:09
 * @Version 1.0.0
 */
@Service
public class SaleInfoBiz {

    @Autowired
    private OrderBiz orderBiz;
    /**
     * saleInfoProduct(商品销售情况统计)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-03 12:09:41
     **/
    public ResponseDto saleInfoProduct() {
        // 统计所有商品销售情况 商品维度 值查询销量前五的数据
        List<ProductTotalVo> productTotalVoList = orderBiz.saleInfoProduct();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResult(productTotalVoList);
        return responseDto;
    }

    /**
     * orderInfoProduct(订单以天维度展示)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-03 13:45:34
     **/
    public ResponseDto orderInfoProduct() {
        List<OrderInfoDaysVo> orderInfoDaysVoList = orderBiz.orderInfoProduct();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(orderInfoDaysVoList);
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }

    /**
     * orderInfoDay(每日销量图)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-03 14:56:20
     **/
    public ResponseDto orderInfoDay() {
        List<OrderDayVo> dayVoList = orderBiz.orderInfoDay();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResult(dayVoList);
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }
}
