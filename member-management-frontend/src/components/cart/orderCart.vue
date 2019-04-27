<!-- 购物车页面-->
<template>
  <div id="cart" style="width:100%;height:100%;margin-top:-10px">
    <mt-header fixed title="购物车" style="height:50px;z-index:100"></mt-header>
    <div class="product-cart" style="margin-top:60px;margin-bottom:120px">
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
    <tabbar :selected="selected"></tabbar>
  </div>
</template>

<script>
import { Checkbox, CheckboxGroup, Card, SubmitBar, Toast } from "vant";
import tabbar from "@/components/tabbar";
import {memberOrderListRequest} from '@/api/user'
export default {
  mounted(){
    // 初始化参数
    this.initProfile();
  },
  data() {
    return {
      checkedGoods: [],
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
      } else {
        this.totalPrice += orderPay  * 100;
      }
      if(this.checkedGoods.length == 0){
        this.totalPrice = 0;
      }
    },

    formatPrice(price) {
      return (price / 100).toFixed(2);
    },
    onSubmit() {
      Toast("点击结算");
    }
  },
  
  components: {
    tabbar: tabbar
  },
};
</script>

<style lang="less">
.card-goods {
  padding: 10px 0;
  background-color: #fff;
  &__item {
    position: relative;
    background-color: #fafafa;
    .van-checkbox__label {
      width: 100%;
      height: auto; // temp
      padding: 0 10px 0 15px;
      box-sizing: border-box;
    }
    .van-checkbox__icon {
      top: 50%;
      left: 10px;
      z-index: 1;
      position: absolute;
      margin-top: -10px;
    }
    .van-card__price {
      color: #f44;
    }
  }
}
.van-submit-bar {
  margin-bottom: 56px;
}
</style>