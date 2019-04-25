package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CodeBuilderUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.OrderCreateRequestDto;
import com.qiqi.member_management.management.business.mapper.OrderMapper;
import com.qiqi.member_management.management.business.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * OrderBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月25号 8:16
 * @Version 1.0.0
 */
@Service
public class OrderBiz {

    private static final Logger logger = LoggerFactory.getLogger(OrderBiz.class);
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MemberInfoBiz memberInfoBiz;
    @Autowired
    private IntegralConfigBiz integralConfigBiz;
    @Autowired
    private OrderRelBiz orderRelBiz;
    @Autowired
    private MemberOrderBiz memberOrderBiz;
    /**
     * createOrder(创建订单)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-25 08:43:59
     **/
    @Transactional
    public ResponseDto createOrder(OrderCreateRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        // 根据memberId查询会员详情
        MemberInfo memberInfo = memberInfoBiz.queryMemberInfoByMemberId(requestDto.getMemberId());
        if (null == memberInfo){
            logger.error(MsgManagement.getMsg(100010));
            throw new BizException(100010);
        }
        // 查询会员积分登记
        IntegralConfig integralConfig = integralConfigBiz.queryInfoByIntegral(memberInfo.getIntegral());
        if (null == integralConfig || null == integralConfig.getLevel()){
            logger.error(MsgManagement.getMsg(100051));
            throw new BizException(100051);
        }
        Order order = new Order();
        // 生成订单编号
        order.setOrderId(CodeBuilderUtil.codeGenerator(orderMapper.getLastOrderInfo().getId(), Contant.ORDER_ID_LENGTH, Contant.ORDER_ID_PREFIX));
        order.setOrderAmount(requestDto.getOrderAmount());
        order.setOrderPay(requestDto.getOrderPay());
        // 真实价格
        order.setRealPay(requestDto.getOrderPay().multiply(integralConfig.getDiscount()));
        // 已下单
        order.setOrderStatus(Contant.ORDER_DONE);
        order.setProductId(requestDto.getProductId());
        saveOrder(order);
        // 创建订单关联信息
        OrderRel orderRel = new OrderRel();
        orderRel.setOrderRelId(CodeBuilderUtil.codeGenerator(orderRelBiz.getLastOrderRel().getId(), Contant.ORDER_REL_ID_LENGTH, Contant.ORDER_REL_ID_PREFIX));
        // 设置订单编号
        orderRel.setOrderId(order.getOrderId());
        // 新增订单关联信息
        orderRelBiz.saveOrderRel(orderRel);
        // 会员订单信息
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setMemberId(requestDto.getMemberId());
        memberOrder.setOrderRelId(orderRel.getOrderRelId());
        memberOrder.setOrderTotalAmount(requestDto.getOrderAmount());
        memberOrder.setOrderTotalPay(requestDto.getOrderPay());
        memberOrder.setRealTotalPay(order.getRealPay());
        memberOrderBiz.saveMemberOrder(memberOrder);
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }


    /**
     * saveOrder(新增订单信息)
     *
     * @Param 
     * @param order
     * @return void
     * @exception 
     * @Date  2019-04-25 09:44:19
     **/
    public void saveOrder(Order order){
        order.setAddedByName(Contant.ADDED_NAME);
        order.setLastModifiedByName(Contant.ADDED_NAME);
        orderMapper.saveOrder(order);
    }
}
