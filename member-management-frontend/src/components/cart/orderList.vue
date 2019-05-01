<!--订单列表页-->
<template>
     <div class="order-list" style="margin-bottom:120px">
           <van-search 
            placeholder="请输入会员名称" 
            shape="round" 
            background="#C6E2FF"
            @input="searchInput()"
            v-model="memberName"
       /> 
           <!--折叠面板使用-->
          <van-collapse 
            v-model="orderListStartIndex" accordion>
            <van-collapse-item v-for="memberOrder in memberOrderList" :key="memberOrder.id" :name="memberOrder.id">
              <!--自定义标题-->
              <div slot="title" align="left">
                    <van-cell :title="memberOrder.memberName">
                        {{memberOrder.orderTime}}
                        <span slot="label">
                            订单金额：￥{{memberOrder.totalOrderPay }} 
                        </span>
                    </van-cell>
              </div>  
                  <van-swipe-cell :right-width="65">
                    <!--商品卡片-->
                    <van-card v-for="item in memberOrder.orderCart" :key="item.id" :title="item.productName" :price="item.price" style="text-align:left">
                      <div slot="footer">
                        商品数量：{{ item.orderAmount }}
                      </div>
                      <div slot="thumb">
                        <img :src="item.productImgSrc" style="width:90px;height:90px"/>
                      </div>
                    </van-card>
                  </van-swipe-cell>
            </van-collapse-item>
          </van-collapse>
          <div >
            <van-cell label="" size="large" align="right">
              <span slot="title">
                <strong>合计:</strong>
                </span>
              <div slot="label">
                ￥{{totalOrderAmount}}
              </div> 
            </van-cell>
          </div>
    </div>
</template>

<script>
import {memberOrderListRequest} from '@/api/user'
import moment from 'moment'
import {Toast} from 'vant'
export default {
    data(){
        return{
            orderListStartIndex:0,
            memberOrderList:[],
            memberName:'',
            // 总金额
            totalOrderAmount:0
        }
    },
    methods:{
        // 初始化会员订单列表
        showOrderLists(){
            memberOrderListRequest({orderType:'20'}).then(data => {
                this.totalOrderAmount = 0;
                const {resCode, resMsg, result} = data;
                if('000000' != resCode){
                    Toast.fail('查询会员订单失败：'+resMsg);
                    return;
                }
                this.memberOrderList = result;
                this.orderListStartIndex = result[0].id;
                result.map(val => {
                  this.totalOrderAmount += val.totalOrderPay;
                })
            })
        },
        // 根据会员名查询会员订单
        searchInput(){
          memberOrderListRequest({orderType:'20',memberName:this.memberName}).then(data => {
                this.totalOrderAmount = 0;
                const {resCode, resMsg, result} = data;
                if('000000' != resCode){
                    Toast.fail('查询会员订单失败：'+resMsg);
                    return;
                }
                this.memberOrderList = result;
                this.orderListStartIndex = result[0].id;
                result.map(val => {
                  this.totalOrderAmount += val.totalOrderPay;
                })
            })
        }
    }
}
</script>

<style>

</style>
