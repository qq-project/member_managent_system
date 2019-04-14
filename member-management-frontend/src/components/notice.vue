<template>
 <div id="noticePage">
    <mt-header fixed title="推广管理">
      <router-link to="/usercenter" slot="left">
      <mt-button icon="back">返回</mt-button>
    </router-link>
    </mt-header>
  
    <mt-navbar fixed class="top" v-model="navbarSelected">
      <mt-tab-item id="1">待发送推广</mt-tab-item>
      <mt-tab-item id="2">已发送推广</mt-tab-item>
    </mt-navbar>

      <!-- tab-container -->
      <mt-tab-container v-model="navbarSelected">
        <mt-tab-container-item id="1">
          <mt-search
            v-model="infoKey"
            cancel-text="取消"
            @keyup.enter.native="searchGrafts(infoKey)"
            placeholder="请输入推广信息关键字">
          </mt-search>
          <div>
            <div class="add-draft" @click="addNotice">
              <img src="../assets/add.png" alt="添加草稿">
              <span>添加推广</span>
            </div>
          </div>
          <div class="member-info">
            <mt-cell-swipe
              :key="draft.draftId"
              v-for="draft in drafts"
              :right="[
                {
                  content: '删除',
                  style: { background: 'red', color: '#fff', width: '80px' },
                  handler: () => deletedraft(draft.draftId)
                }
              ]">
              <div>
                <div class="my-cell-title">
                  <span>{{draft.title}}</span>
                  <span>{{draft.time}}</span>
                </div>
                <p class="my-cell-content">{{draft.content}}</p>
              </div>
            </mt-cell-swipe>
          </div>

        </mt-tab-container-item>

        <mt-tab-container-item id="2">
          <mt-search
            v-model="orderKey"
            cancel-text="取消"
            @keyup.enter.native="searchMessages(orderKey)"
            placeholder="请输入推广信息关键字">
          </mt-search>
          <div class="order-info">
            <mt-cell-swipe
              :key="message.orderId"
              v-for="message in messages"
              :right="[
                {
                  content: '删除',
                  style: { background: 'red', color: '#fff', width: '80px' },
                  handler: () => deletemessage(message.orderId)
                }
              ]">
              <div>
                <div class="my-cell-title">
                  <span>{{message.title}}</span>
                  <span>{{message.time}}</span>
                </div>
                <p class="my-cell-content">{{message.content}}</p>
              </div>
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
        selected: '',
        drafts: [],
        messages: [],

      }
    },
    mounted() {
      // TODO：初始化draft 列表, 调用查询全部api
      this.initList()
    },  
    methods: {
      initList() {
        this.drafts = [
          {
            draftId: 1,
            title: "荷叶茶",
            content: '【惠民超市】悄悄送你4元买荷叶茶的机会，还有祛湿茶1折起，点击领取 7pd.cn/1rdmGZbk 回T退订',
            time: moment().format('YYYY-MM-DD')
          },
          {
            draftId: 2,
            title: "手串",
            content: '【惠民超市】来自莆田的<5.9元买手串>特权送你，仅100位，点击查看 7pd.cn/bnemNldU 回T退订',
            time: moment().format('YYYY-MM-DD')
          },
          {
            draftId: 3,
            title: "当归",
            content: '【惠民超市】请查收~您的福利包裹到了！→当归7.5元，荷叶茶1折起点击验货 7pd.cn/FqKmkgNl 回TD退订',
            time: moment().format('YYYY-MM-DD')
          }
        ]
      },
      searchGrafts(infoKey) {
        console.log(infoKey);
        // TODO: 根据关键字infoKey搜索， 调用查询api
        this.drafts = [
          {
            draftId: 1,
            title: "荷叶茶",
            content: '【惠民超市】悄悄送你4元买荷叶茶的机会，还有祛湿茶1折起，点击领取 7pd.cn/1rdmGZbk 回T退订',
            time: moment().format('YYYY-MM-DD')
          },
          {
            draftId: 2,
            title: "手串",
            content: '【惠民超市】来自莆田的<5.9元买手串>特权送你，仅100位，点击查看 7pd.cn/bnemNldU 回T退订',
            time: moment().format('YYYY-MM-DD')
          },
          {
            draftId: 3,
            title: "当归",
            content: '【惠民超市】请查收~您的福利包裹到了！→当归7.5元，荷叶茶1折起点击验货 7pd.cn/FqKmkgNl 回TD退订',
            time: moment().format('YYYY-MM-DD')
          }
        ]
      },
      searchMessages(orderKey) {
        console.log(orderKey);
        // TODO: 根据关键字infoKey搜索， 调用查询api
        this.messages = [
          {
            orderId: 91,
            type: '日常用品',
            pay: 102,
            contime: moment().format('YYYY-MM-DD')
          },
          {
            orderId: 92,
            type: '食品',
            pay: 55,
            conTime: moment("2010-10-20 4:30").format('YYYY-MM-DD HH:mm')
          }
        ]
      },      
      deletedraft(draftId) {
        this.$messagebox.confirm('确定删除？').then(
          action => {         
            // TODO: 调用删除api
            Toast("已成功删除" + draftId)
          },
          cancel => {

          }
        )
      },
      deletemessage(orderId) {
          this.$messagebox.confirm('确定删除？').then(
          action => {         
            // TODO: 调用删除api
            Toast("已成功删除" + orderId)
          },
          cancel => {

          }
        )
      },
      addNotice() {
        // TODO: 进入增加推广页面
        Toast("成功添加推广");
      }
    },
		watch:{
      navbarSelected:function(val, oldVal){
        if('2' === val){
          this.messages = [
            {
              draftId: 1,
              title: "荷叶茶",
              content: '【惠民超市】悄悄送你4元买荷叶茶的机会，还有祛湿茶1折起，点击领取 7pd.cn/1rdmGZbk 回T退订',
              time: moment().format('YYYY-MM-DD')
            },
            {
              draftId: 2,
              title: "手串",
              content: '【惠民超市】来自莆田的<5.9元买手串>特权送你，仅100位，点击查看 7pd.cn/bnemNldU 回T退订',
              time: moment().format('YYYY-MM-DD')
            },
            {
              draftId: 3,
              title: "当归",
              content: '【惠民超市】请查收~您的福利包裹到了！→当归7.5元，荷叶茶1折起点击验货 7pd.cn/FqKmkgNl 回TD退订',
              time: moment().format('YYYY-MM-DD')
            }        
          ]
        }
      }
    }
  }
</script>
<style>
#noticePage .top {
  top: 60px;
  width: 100%;
  z-index: 2;
}

#noticePage .add-draft {
  width: 100%;
  height: 50px;
  display: flex;
  justify-items: flex-start;
  padding-left: 10px;
  padding-top: 15px;
}

#noticePage .add-draft img{
  width: 30px;
  height: 30px;
}

#noticePage .add-draft span{
  width: 100px;
  height: 30px;
  line-height: 30px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #1296db;
}

#noticePage .my-cell-title {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
  height: 30px;
  line-height: 30px;
  font-size: 18px;
  font-weight: bold;
  font-family: 'Courier New', Courier, monospace;
}

#noticePage .my-cell-content {
  padding: 10px;
  font-size: 14px;
}

#noticePage .mint-header.is-fixed {
  z-index: 2;
}

#noticePage .member-info, .order-info {
  height: 100%;
  margin-bottom: 55px;
}

#noticePage .mint-tab-container {
  margin-top: 105px;
}

#noticePage .mint-searchbar {
  position: fixed;
  width: 100%;
}

#noticePage .mint-cell-value {
  display: flex;
  justify-content: space-around;
  width: 100%;
  margin-bottom: 1px solid;
}

#noticePage .mint-cell-left, #noticePage .mint-cell-wrapper {
  transform: translate3d(0px, 0px, 0px);
  background-image: none;
}

#noticePage .description {
  display: flex;
  justify-content: space-around;
  width: 100%;
  height: 50px;
  line-height: 50px;
  margin-bottom: 1px solid;
}
</style>
