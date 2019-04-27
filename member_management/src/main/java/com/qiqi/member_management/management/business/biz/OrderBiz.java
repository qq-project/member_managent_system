package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CodeBuilderUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.OrderCreateRequestDto;
import com.qiqi.member_management.management.business.mapper.OrderMapper;
import com.qiqi.member_management.management.business.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
    @Autowired
    private StorageInfoBiz storageInfoBiz;

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
        responseDto.setResCode(ResponseDto.SUCCESS);
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
        // 根据 订单状态判断该订单是 10 加入购物车,20 已下单
        Order order = null;
        // 加入购物车 默认 同一会员下，同一商品的 加入购物车状态只有一个
        if (Contant.ORDER_UNDO.equals(requestDto.getOrderStatus())){
            order = queryOrderByProductId(requestDto.getProductId(),Contant.ORDER_UNDO);
        }
        // 此时第一次添加 或者直接购买
        if (null == order){
            handleFirstOrderInsert(requestDto, integralConfig.getDiscount());
        } else {
            // 此处表示用户添加购物车同一商品
            handleAddCart(requestDto, integralConfig.getDiscount(), order);
        }
        // 处理商品库存
        
        return responseDto;
    }

    /**
     * handleFirstOrderInsert(第一次创建订单处理)
     *
     * @Param 
     * @param requestDto
     * @param discount 会员享受折扣
     * @return void
     * @exception 
     * @Date  2019-04-27 09:33:28
     **/
    public void handleFirstOrderInsert(OrderCreateRequestDto requestDto, BigDecimal discount){
        //保存订单
        Order order = new Order();
        // 生成订单编号
        order.setOrderId(CodeBuilderUtil.codeGenerator(orderMapper.getLastOrderInfo().getId(), Contant.ORDER_ID_LENGTH, Contant.ORDER_ID_PREFIX));
        order.setOrderAmount(requestDto.getOrderAmount());
        order.setOrderPay(requestDto.getOrderPay());
        // 真实价格
        order.setRealPay(requestDto.getOrderPay().multiply(discount));
        // 已下单
        order.setOrderStatus(requestDto.getOrderStatus());
        order.setProductId(requestDto.getProductId());
        saveOrder(order);
        // 创建订单关联信息
        OrderRel orderRel = new OrderRel();
        orderRel.setOrderRelId(CodeBuilderUtil.codeGenerator(orderRelBiz.getLastOrderRel().getId(), Contant.ORDER_REL_ID_LENGTH, Contant.ORDER_REL_ID_PREFIX));
        // 设置订单编号
        orderRel.setOrderId(order.getOrderId());
        // 新增订单关联信息
        orderRelBiz.saveOrderRel(orderRel);
        // 会员订单信息 当订单状态为加入购物车，只新增一条会员，不进行金额处理
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setMemberId(requestDto.getMemberId());
        memberOrder.setOrderRelId(orderRel.getOrderRelId());
        if (Contant.ORDER_DONE.equals(requestDto.getOrderStatus())){
            memberOrder.setOrderTotalAmount(requestDto.getOrderAmount());
            memberOrder.setOrderTotalPay(requestDto.getOrderPay());
            memberOrder.setRealTotalPay(order.getRealPay());
        }
        memberOrderBiz.saveMemberOrder(memberOrder);
    }

    /**
     * handleAddCart(添加购物车请求)
     *
     * @Param 
     * @param requestDto
     * @param discount
     * @param order
     * @return void
     * @exception 
     * @Date  2019-04-27 09:39:27
     **/
    public void handleAddCart(OrderCreateRequestDto requestDto, BigDecimal discount, Order order){
        // 订单数量增加
        order.setOrderAmount(order.getOrderAmount() + requestDto.getOrderAmount());
        order.setOrderPay(order.getOrderPay().add(requestDto.getOrderPay()));
        // 真实价格 (原 + 新) * dis
        order.setRealPay(order.getRealPay().add(requestDto.getOrderPay()).multiply(discount));
        // 未下单
        order.setOrderStatus(Contant.ORDER_UNDO);
        // 修改原订单
        orderMapper.updateOrder(order);
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

    /**
     * queryOrderByProductId(根据商品编号和订单状态查询订单信息)
     *
     * @Param 
     * @param productId
     * @param orderStatus
     * @return com.qiqi.member_management.management.business.model.Order
     * @exception 
     * @Date  2019-04-27 09:20:57
     **/
    public Order queryOrderByProductId(String productId, String orderStatus){
        Order order = new Order();
        order.setProductId(productId);
        order.setOrderStatus(orderStatus);
        return orderMapper.queryOrder(order);
    }
}
