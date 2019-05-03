<!-- 购物车页面-->
<template>
  <div id="cart" style="width:100%;height:100%;margin-top:-10px">
    <mt-header fixed title="订单中心" style="height:50px;z-index:100"></mt-header>
    <van-tabs type="line" style="margin-top:65px" @click="tabsClick">
      <van-tab title="购物车">
        <div class="product-cart" style="margin-bottom:120px">
          <!--折叠面板使用-->
          <van-collapse 
            v-model="memberOrderStartIndex"
            @change="changeCollapse()"
            accordion>
            <van-collapse-item
              v-for="memberOrder in memberOrderList"
              :key="memberOrder.id" 
              :name="memberOrder.id" 
              size="large"
              >
              <!--自定义标题-->
              <div slot="title" align="left">
              <van-icon name="shop-o" size="12px"/>
                  {{ memberOrder.memberName }}     享受{{ memberOrder.discount }}
              </div>  
              <van-checkbox-group class="card-goods" v-model="checkedGoods" @change="checkGroupChange()">
                <van-checkbox
                  class="card-goods__item"
                  v-for="item in memberOrder.orderCart"
                  :key="item.id"
                  :name="item.id"
                  @click="chosenProducts(item)"
                >
                  <van-swipe-cell :right-width="65">
                    <!--商品卡片-->
                    <van-card
                      :title="item.productName"
                      :price="item.price"
                      style="text-align:left"
                    >
                      <div slot="footer">
                        库存：{{ item.productAmount }}
                      </div>
                      <div slot="thumb">
                        <img :src="item.productImgSrc" style="width:90px;height:90px"/>
                      </div>
                      <div slot="num" @click.stop>
                        <van-stepper 
                          v-model="item.orderAmount"
                          @change="orderAmountChange(item)"
                          :disable-input="isDisableStep"
                          :max="item.productAmount"/>
                      </div>
                    </van-card>
                    <!--滑动删除-->
                    <div slot="right">
                      <van-button type="danger" class="del-btn" @click="delCartOrder(item)">删除</van-button>
                    </div>
                  </van-swipe-cell>
                </van-checkbox>
              </van-checkbox-group>
            </van-collapse-item>
          </van-collapse>
        </div>
      <div>
        <van-submit-bar
          :price="totalPrice"
          :disabled="!checkedGoods.length"
          :button-text="submitBarText"
          @submit="onSubmit"
        />
      </div>
      </van-tab>
      <van-tab title="会员订单">
        <orderList ref="orderList"></orderList>
        </van-tab>
    </van-tabs>
    <tabbar :selected="selected"></tabbar> 

  </div>
</template>

<script>
import { Checkbox, CheckboxGroup, Card, SubmitBar, Toast } from "vant";
import tabbar from "@/components/tabbar";
import {memberOrderListRequest, memberOrderSubmitRequest, cartOrderDelRequest} from '@/api/user'
import orderList from './orderList'
export default {
  mounted(){
    // 初始化参数
    this.initProfile();
  },
  data() {
    return {
      checkedGoods: [],
      checkedProducts:[],
      memberOrderList:[],
      memberOrderStartIndex:0,
      selected: "cart",
      // 折叠面板数组
      isDisableStep:false,
      // 总计金额
      totalPrice:0,
    };
  },
  computed: {
    submitBarText() {
      const count = this.checkedGoods.length;
      return "结算" + (count ? `(${count})` : "");
    },
  },
  methods: {
    // 初始化数据
    initProfile(){
      // 10 表示购物车
      memberOrderListRequest({orderType:'10'}).then(data => {
        const {resCode, resMsg, result} = data;
        if('000000' != resCode){
          Toast.fail('查询会员订单失败：'+resMsg);
          return;
        }
        this.memberOrderList = result;
        this.memberOrderStartIndex = result[0].id;
      })
      },
    // 商品数量改变触发
    orderAmountChange(val){
      const {orderAmount, productAmount, price, id} = val;
      if(orderAmount > productAmount){
        Toast.fail({
          message:'订单数量不得大于库存数量',
          duration:1000
        })
        return
      }
      
      let orderPayNew = orderAmount * price;
      if(this.checkedGoods.indexOf(val.id) != -1){
        this.totalPrice += (orderPayNew - val.orderPay) * 100;
      } 
      val.orderPay = orderPayNew;
    },
    // 下拉面板切换
    changeCollapse(){
      this.checkedGoods = [];
      this.totalPrice = 0;
    },
    // 复选组改变
    checkGroupChange(val){
    },
    // 选择商品
    chosenProducts(val){
      const {id, orderPay} = val;
      // 判断是否被选中
      if(this.checkedGoods.indexOf(val.id) == -1 && 0 != this.totalPrice){
        this.totalPrice -= orderPay * 100;
        this.checkedProducts.splice(this.checkedProducts.indexOf(val),1);
      } else {
        this.totalPrice += orderPay  * 100;
        this.checkedProducts.push(val);
      }
      if(this.checkedGoods.length == 0){
        this.totalPrice = 0;
        this.checkedProducts = [];
      }
    },
    // 结算
    onSubmit() {
      memberOrderSubmitRequest({orderCarts:this.checkedProducts}).then(data => {
        const {resCode, resMsg} = data;
        if('000000' !== resCode){
          Toast.fail({
            message:'下单失败：' +resMsg,
            duration:1500
          })
          return;
        }
        // 下单成功
        Toast.success({
          message:'结算成功！',
          duration:1500
        })
        this.initProfile();
      })
    },
    // 删除购车订单
    delCartOrder(item){
      cartOrderDelRequest(item).then(data => {
        const {resCode, resMsg} = data;
        if('000000' != resCode){
          Toast.fail({
            message:resMsg,
            duration:1500
          })
          return;
        }
        // 下单成功
        Toast.success({
          message:'删除成功！',
          duration:1500
        })
        this.initProfile();
      })
    },
    // 点击标签切换
    tabsClick(index, title){
      if(undefined == this.$refs.orderList){
        return;
      }
      if('会员订单' === title){
        this.$refs.orderList.showOrderLists();
      }
    }
  },
  
  components: {
    tabbar: tabbar,
    orderList:orderList
  },
};
</script>

<style>
.van-submit-bar {
  margin-bottom: 56px;
}


.del-btn{
  height:120px;
  width:auto
}
</style>