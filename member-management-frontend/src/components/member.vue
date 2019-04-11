<template>
 <div id="memberPage">
    <mt-header fixed title="会员管理"></mt-header>
  
    <mt-navbar fixed class="top" v-model="navbarSelected">
      <mt-tab-item id="1">会员信息</mt-tab-item>
      <mt-tab-item id="2">会员订单</mt-tab-item>
    </mt-navbar>

      <!-- tab-container -->
      <mt-tab-container v-model="navbarSelected">
        <mt-tab-container-item id="1">
          <mt-search
            v-model="infoKey"
            cancel-text="取消"
            @keyup.enter.native="searchMember(infoKey)"
            placeholder="请输入姓名或者电话号码">
          </mt-search>
          <div class="description">
            <p>姓名</p>
            <p>电话</p>
            <p>积分</p>
          </div>
          <div class="member-info">
            <mt-cell-swipe
              :key="memberInfo.memberId"
              v-for="memberInfo in memberInfos"
              :right="[
                {
                  content: '删除',
                  style: { background: 'red', color: '#fff', width: '80px' },
                  handler: () => deleteMemberInfo(memberInfo.memberId)
                }
              ]">
              <p>{{memberInfo.name}}</p>
              <p>{{memberInfo.tel}}</p>
              <p>{{memberInfo.integral}}</p>
            </mt-cell-swipe>
          </div>

        </mt-tab-container-item>

        <mt-tab-container-item id="2">
          <mt-search
            v-model="orderKey"
            cancel-text="取消"
            @keyup.enter.native="searchOrder(orderKey)"
            placeholder="请输入商品类型或者会员名">
          </mt-search>
          <div class="description">
            <p>商品类型</p>
            <p>实付金额</p>
            <p>消费时间</p>
          </div>
          <div class="order-info">
            <mt-cell-swipe
              :key="orderInfo.orderId"
              v-for="orderInfo in orderInfos"
              :right="[
                {
                  content: '删除',
                  style: { background: 'red', color: '#fff', width: '80px' },
                  handler: () => deleteOrderInfo(orderInfo.orderId)
                }
              ]">
                <p>{{orderInfo.type}}</p>
                <p>{{orderInfo.pay}}</p>
                <p>{{orderInfo.conTime}}</p>
            </mt-cell-swipe>
          </div>
        </mt-tab-container-item>
      </mt-tab-container>

  <tabbar :selected="selected"></tabbar>
 </div>

</template>
<script>
import { Search, Cell, Header, Navbar, TabItem, CellSwipe, MessageBox, Toast  } from 'mint-ui';
import tabbar from './tabbar';
import moment from "moment";
  export default{
    components: {tabbar},
    data(){
      return {
        navbarSelected: '1',
        infoKey: '',
        orderKey: '',
        selected: 'member',
        memberInfos: [],
        orderInfos: [],

      }
    },
    mounted() {
      // TODO：初始化memberInfo 列表, 调用查询全部api
      this.initList()
    },  
    methods: {
      initList() {
        this.memberInfos = [
          {
            memberId: 1,
            name: '王伟',
            tel: '14752558213',
            integral: 300
          },
          {
            memberId: 2,
            name: '李文',
            tel: '18153843081',
            integral: 280
          },
          {
            memberId: 3,
            name: '赵苏',
            tel: '15062154310',
            integral: 80
          },
          {
            memberId: 4,
            name: '可乐',
            tel: '15062154310',
            integral: 5
          },
          {
            memberId: 5,
            name: '欧艾斯',
            tel: '15062154310',
            integral: 10080
          },
          {
            memberId: 6,
            name: '萨拉',
            tel: '15062154310',
            integral: 80
          },          
          {
            memberId: 7,
            name: '大娜迦',
            tel: '15062154310',
            integral: 1080
          },          
          {
            memberId: 8,
            name: '戴斯',
            tel: '15062154310',
            integral: 800
          },
          {
            memberId: 9,
            name: '莫德凯撒',
            tel: '15062154310',
            integral: 1080
          },          
          {
            memberId: 10,
            name: '大叔',
            tel: '15062154310',
            integral: 800
          },          
          {
            memberId: 11,
            name: '褚珅',
            tel: '15062154310',
            integral: 55
          },          
          {
            memberId: 12,
            name: '钱明达',
            tel: '15062154310',
            integral: 584
          }
        ]
      },
      searchMember(infoKey) {
        console.log(infoKey);
        // TODO: 根据关键字infoKey搜索， 调用查询api
        this.memberInfos = [
          {
            memberId: 1,
            name: '王伟',
            tel: '14752558213',
            integral: 300
          },
          {
            memberId: 2,
            name: '王则',
            tel: '14752558213',
            integral: 600
          },
        ]
      },
      searchOrder(orderKey) {
        console.log(orderKey);
        // TODO: 根据关键字infoKey搜索， 调用查询api
        this.orderInfos = [
          {
            orderId: 91,
            type: '日常用品',
            pay: 102,
            conTime: moment().format('YYYY-MM-DD HH:mm')
          },
          {
            orderId: 92,
            type: '食品',
            pay: 55,
            conTime: moment("2010-10-20 4:30").format('YYYY-MM-DD HH:mm')
          }
        ]
      },      
      deleteMemberInfo(memberId) {
        this.$messagebox.confirm('确定删除？').then(
          action => {         
            // TODO: 调用删除api
            Toast("已成功删除" + memberId)
          },
          cancel => {

          }
        )
      },
      deleteOrderInfo(orderId) {
          this.$messagebox.confirm('确定删除？').then(
          action => {         
            // TODO: 调用删除api
            Toast("已成功删除" + orderId)
          },
          cancel => {

          }
        )
      }
    },
		watch:{
      navbarSelected:function(val, oldVal){
        if('2' === val){
          this.orderInfos = [
             {
            orderId: 91,
            type: '日常用品',
            pay: 102,
            conTime: moment().format('YYYY-MM-DD HH:mm')
          },
          {
            orderId: 92,
            type: '食品',
            pay: 55,
            conTime: moment("2010-10-20 4:30").format('YYYY-MM-DD HH:mm')
          },
          {
            orderId: 93,
            type: '日常用品',
            pay: 102,
            conTime: moment().format('YYYY-MM-DD HH:mm')
          },
          {
            orderId: 94,
            type: '日常用品',
            pay: 102,
            conTime: moment().format('YYYY-MM-DD HH:mm')
          },
          {
            orderId: 95,
            type: '服装',
            pay: 102,
            conTime: moment().format('YYYY-MM-DD HH:mm')
          }         
          ]
        }
      }
    }
  }
</script>
<style>
#memberPage .top {
  top: 60px;
  width: 100%;
  z-index: 2;
}

#memberPage .mint-header.is-fixed {
  z-index: 2;
}

#memberPage .member-info, .order-info {
  height: 100%;
  margin-bottom: 55px;
}

#memberPage .mint-tab-container {
  margin-top: 105px;
}

#memberPage .mint-searchbar {
  position: fixed;
  width: 100%;
}

#memberPage .mint-cell-value {
  display: flex;
  justify-content: space-around;
  width: 100%;
  margin-bottom: 1px solid;
}

#memberPage .mint-cell-left, #memberPage .mint-cell-wrapper {
  transform: translate3d(0px, 0px, 0px);
  background-image: none;
}

#memberPage .description {
  display: flex;
  justify-content: space-around;
  width: 100%;
  height: 50px;
  line-height: 50px;
  margin-bottom: 1px solid;
}
</style>
