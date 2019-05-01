package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.DateConvertUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.MemberOrderListRequestDto;
import com.qiqi.member_management.management.business.dto.request.MemberOrderSubmitRequestDto;
import com.qiqi.member_management.management.business.mapper.MemberOrderMapper;
import com.qiqi.member_management.management.business.mapper.OrderMapper;
import com.qiqi.member_management.management.business.model.*;
import com.qiqi.member_management.management.business.model.model.extend.MemberIntegralInfo;
import com.qiqi.member_management.management.business.vo.MemberOrderListVo;
import com.qiqi.member_management.management.business.vo.OrderListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * MemberOrderBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月25号 8:17
 * @Version 1.0.0
 */
@Service
public class MemberOrderBiz {

    @Autowired
    private MemberOrderMapper memberOrderMapper;
    @Autowired
    /**
     * 解决循环注入问题  待优化
     **/
    @Lazy 
    private OrderBiz orderBiz;
    @Autowired
    private MemberInfoBiz memberInfoBiz;
    @Autowired
    private IntegralConfigBiz integralConfigBiz;
    @Autowired
    private ProductInfoBiz productInfoBiz;
    @Autowired
    private StorageInfoBiz storageInfoBiz;

    private static final Logger logger = LoggerFactory.getLogger(MemberOrderBiz.class);

    /**
     * queryMemberOrderList(查询会员订单列表)
     *
     * @Param
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception
     * @Date  2019-04-27 10:54:14
     **/
    public ResponseDto queryMemberOrderList(MemberOrderListRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        // 实际返回的vo
        List<MemberOrderListVo> memberOrderListVoList = handleResultVo(requestDto);
        if (null == memberOrderListVoList || memberOrderListVoList.size() == 0){
            logger.warn(MsgManagement.getMsg(100061));
        }
        responseDto.setResult(memberOrderListVoList);
        return responseDto;
    }

    /**
     * saveMemberOrder(保存会员订单信息)
     *
     * @Param
     * @param memberOrder
     * @return void
     * @exception
     * @Date  2019-04-25 10:16:09
     **/
    public void saveMemberOrder(MemberOrder memberOrder){
        memberOrder.setAddedByName(Contant.ADDED_NAME);
        memberOrder.setLastModifiedByName(Contant.ADDED_NAME);
        memberOrderMapper.saveMemberOrder(memberOrder);
    }


    /**
     * handleResultVo(处理返回的会员订单列表)
     *
     * @Param
     * @param requestDto
     * @return java.util.List<com.qiqi.member_management.management.business.vo.MemberOrderListVo>
     * @exception
     * @Date  2019-04-27 11:48:05
     **/
    private List<MemberOrderListVo> handleResultVo(MemberOrderListRequestDto requestDto) {
        // 查询所有的会员订单信息
        List<MemberOrder> memberOrderList = memberOrderMapper.queryMemberOrderList(requestDto);
        // 实际返回的vo
        List<MemberOrderListVo> memberOrderListVoList = new ArrayList<>();
        if (null != memberOrderList && memberOrderList.size() > 0) {
            for (MemberOrder memberOrder : memberOrderList) {
                // 根据会员编号，查询实际order
                List<OrderListVo> orderList = orderBiz.queryOrderLists(memberOrder.getOrderId(), requestDto.getOrderType());
                if (null != orderList && orderList.size() > 0) {
                    // 根据memberId 查询会员信息
                    MemberInfo memberInfo = memberInfoBiz.queryMemberInfoByMemberId(memberOrder.getMemberId());
                    if (null == memberInfo) {
                        logger.error(MsgManagement.getMsg(100010));
                        throw new BizException(100010);
                    }
                    // 查询会员积分登记
                    IntegralConfig integralConfig = integralConfigBiz.queryInfoByIntegral(memberInfo.getIntegral());
                    if (null == integralConfig || null == integralConfig.getLevel()) {
                        logger.error(MsgManagement.getMsg(100051));
                        throw new BizException(100051);
                    }
                    MemberOrderListVo memberOrderListVo = new MemberOrderListVo();
                    memberOrderListVo.setId(memberOrder.getId());
                    memberOrderListVo.setMemberName(memberInfo.getMemberName());
                    memberOrderListVo.setMemberId(memberInfo.getMemberId());
                    memberOrderListVo.setDiscount(integralConfig.getDiscount());
                    memberOrderListVo.setOrderCart(orderList);
                    memberOrderListVo.setTotalOrderPay(memberOrder.getOrderTotalPay());
                    // 下单时间
                    memberOrderListVo.setOrderTime(DateConvertUtil.convertDateToString(memberOrder.getLastModifiedTime()));
                    memberOrderListVoList.add(memberOrderListVo);
                }
            }
        }
        return memberOrderListVoList;
    }

    /**
     * queryUnDoneMemberOrder(查询会员订单)
     *
     * @Param 
     * @param memberId
     * @param orderStatus
     * @return com.qiqi.member_management.management.business.model.MemberOrder
     * @exception 
     * @Date  2019-04-27 12:38:03
     **/
    public MemberOrder queryUnDoneMemberOrder(String memberId, String orderStatus) {
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setMemberId(memberId);
        memberOrder.setOrderType(orderStatus);
        return queryMemberOrder(memberOrder);
    }

    /**
     * queryMemberOrder(查询会员信息)
     *
     * @Param 
     * @param memberOrder
     * @return com.qiqi.member_management.management.business.model.MemberOrder
     * @exception 
     * @Date  2019-04-27 12:39:29
     **/
    public MemberOrder queryMemberOrder(MemberOrder memberOrder){
        return memberOrderMapper.queryMemberOrder(memberOrder);
    }

    /**
     * getLastOrderInfo(获取最新会员订单信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.request.OrderCreateRequestDto
     * @exception 
     * @Date  2019-04-27 12:52:32
     **/
    public MemberOrder getLastOrderInfo() {
        return memberOrderMapper.getLastOrderInfo();
    }

    /**
     * memberOrderSubmit(购物车下单请求)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-28 08:24:51
     **/
    @Transactional
    public ResponseDto memberOrderSubmit(MemberOrderSubmitRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        // 获取订单列表
        List<Order> orderCarts = requestDto.getOrderCarts();
        if (null == orderCarts || orderCarts.size() == 0){
            logger.error(MsgManagement.getMsg(100061));
            throw new BizException(100061);
        }
        // 因为同一会员下，只允许有一个状态为 购物车的 memberOrder，所以，orderId 相同
        // 获取会员订单
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setOrderId(orderCarts.get(0).getOrderId());
        memberOrder = queryMemberOrder(memberOrder);
        if (null == memberOrder){
            logger.error(MsgManagement.getMsg(100061));
            throw new BizException(100061);
        }
        // 获取该会员订单下所有状态为 10的订单  便于后面比较
        List<OrderListVo> orderListBeforeUpdate = orderBiz.queryOrderLists(memberOrder.getOrderId(), Contant.ORDER_UNDO);
        // 获取会员积分信息
        MemberIntegralInfo memberIntegralInfo = memberInfoBiz.getMemberIntegralInfo(memberOrder.getMemberId());
        // 订单总金额
        BigDecimal totalOrderPay = BigDecimal.ZERO;
        // 实付总金额
        BigDecimal totalRealPay = BigDecimal.ZERO;
        for (Order order : orderCarts){
            // 根据 productId 获取产品信息
             ProductInfo productInfo = productInfoBiz.queryProductInfo(order.getProductId());
             if (null == productInfo){
                 logger.info(MsgManagement.getMsg(100036));
                 throw new BizException(100036);
             }
            // 统计总金额 商品 价格 * 数量
            System.out.println(new BigDecimal(order.getOrderAmount()).multiply(productInfo.getPrice()));
            totalOrderPay = (new BigDecimal(order.getOrderAmount()).multiply(productInfo.getPrice())).add(totalOrderPay);
             // 会员实付金额
            totalRealPay = totalOrderPay.multiply(memberIntegralInfo.getIntegralConfig().getDiscount());
            handleOrderSubmit(order, memberIntegralInfo, productInfo);
        }
        // 判断该会员订单下是否仍旧有 状态为 10 的订单
        if (orderListBeforeUpdate.size() == orderCarts.size()){
            // 此处表示该会员订单下的购物车已经全部下单
            memberOrder.setOrderType(Contant.ORDER_DONE);
        }
        memberOrder.setOrderTotalPay(totalOrderPay);
        memberOrder.setRealTotalPay(totalRealPay);
        // 更新会员订单信息
        updateMemberInfo(memberOrder);
        // 更新会员积分
        MemberInfo memberInfo = memberIntegralInfo.getMemberInfo();
        // 积分政策 ￥10 = 1 积分
        memberInfo.setIntegral(memberInfo.getIntegral() + totalOrderPay.multiply(memberIntegralInfo.getIntegralConfig().getRate()).intValue());
        // 更新会员信息
        memberInfoBiz.updateMemberInfo(memberInfo);
        return responseDto;
    }

    /**
     * updateMemberInfo(更新会员订单信息)
     *
     * @Param 
     * @param memberOrder
     * @return void
     * @exception 
     * @Date  2019-05-01 10:50:07
     **/
    public void updateMemberInfo(MemberOrder memberOrder){
        memberOrderMapper.updateMemberInfo(memberOrder);
    }

    /**
     * handleOrderSubmit(处理订单提交)
     *
     * @Param
     * @param order
     * @param memberIntegralInfo
     * @param productInfo
     * @return void
     * @exception
     * @Date  2019-04-28 08:37:25
     **/
    private void handleOrderSubmit(Order order, MemberIntegralInfo memberIntegralInfo, ProductInfo productInfo){
        // 1、判断库存并更新
        StorageInfo storageInfo = storageInfoBiz.queryStorageByProductId(productInfo.getProductId());
        // 商品数量大于库存数量
        if (storageInfo.getProductAmount() < order.getOrderAmount()){
            logger.error(MsgManagement.getMsg(100062));
            throw new BizException(100062);
        }
        storageInfo.setProductAmount(storageInfo.getProductAmount() - order.getOrderAmount());
        storageInfoBiz.updateStorageInfo(storageInfo);
        // 2、更新order信息
        order.setOrderPay(new BigDecimal(order.getOrderAmount()) .multiply(productInfo.getPrice()));
        order.setRealPay(order.getOrderPay().multiply(memberIntegralInfo.getIntegralConfig().getDiscount()));
        order.setOrderStatus(Contant.ORDER_DONE);
        // 2、更新库存信息
        orderBiz.updateOrder(order);
    }


}
