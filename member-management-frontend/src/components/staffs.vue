<template>
 <div id="staffPage">
    <mt-header fixed title="员工管理" style="height:50px">
      <router-link to="/usercenter" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
      <img @click="addUser" class="add-person" src="../assets/add-person.png" slot="right"/>
    </mt-header>

    <mt-search
      v-model="infoKey"
      cancel-text="取消"
      @keyup.enter.native="searchUser(infoKey)"
      placeholder="请输入姓名或者电话号码">
    </mt-search>
    <div class="member-info">
      <mt-cell-swipe
        :key="userInfo.userId"
        v-for="userInfo in userInfos"
        :right="[
          {
            content: '删除',
            style: { background: 'red', color: '#fff', width: '80px' },
            handler: () => deleteuserInfo(userInfo.userId)
          }
        ]">
        
        <div class="items" @click="updateUser(userInfo.userId)">
          <img class="user-avatar" :src="userInfo.avatar" alt="用户头像">
          <p>{{userInfo.name}}</p>
          <p>tel: {{userInfo.tel}}</p>
          <img class="my-more" src="../assets/right.png" alt="查看">
        </div>
      </mt-cell-swipe>
    </div>
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
        infoKey: '',
        selected: 'member',
        userInfos: [],
        orderInfos: [],

      }
    },
    mounted() {
      // TODO：初始化userInfo 列表, 调用查询全部api
      this.initList()
    },  
    methods: {
      initList() {
        this.userInfos = [
          {
            userId: 1,
            name: '王伟',
            tel: '14752558213',
            avatar: require('../assets/rubber.png')
          },
          {
            userId: 2,
            name: '李文',
            tel: '18153843081',
            avatar: require('../assets/ruler.png')
          },
          {
            userId: 3,
            name: '赵苏好',
            tel: '15062154310',
            avatar: require('../assets/fruit.png')
          },
          {
            userId: 4,
            name: '可乐',
            tel: '15062154310',
            avatar: require('../assets/ruler.png')
          }
        ]
      },
      searchUser(infoKey) {
        console.log(infoKey);
        // TODO: 根据关键字infoKey搜索， 调用查询api
        this.userInfos = [
          {
            userId: 1,
            name: '王伟',
            tel: '14752558213',
            avatar: 300
          },
          {
            userId: 2,
            name: '王则',
            tel: '14752558213',
            avatar: 600
          },
        ]
      },
      updateUser(userId) {
        // TODO: 进入编辑user界面
        console.log(userId);
        this.$router.push({ path: '/userInfo', query:{ userId: userId } });
      },
      addUser() {
        // TODO：进入添加员工页面
        Toast("添加员工成功");
      },  
      deleteuserInfo(userId) {
        this.$messagebox.confirm('确定删除？').then(
          action => {         
            // TODO: 调用删除api
            Toast("已成功删除" + userId)
          },
          cancel => {

          }
        )
      }
    },
  }
</script>
<style>
#staffPage .top {
  top: 60px;
  width: 100%;
  z-index: 2;
}

#staffPage .items {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: space-between;
  line-height: 30px;
}

#staffPage .items .my-more {
  width: 10px;
  height: 10px;
  margin-top: 10px;
}

#staffPage .user-avatar {
  width: 30px;
  height: 30px;
}

#staffPage .add-person {
  width: 30px;
  height: 30px;
}

#staffPage .add-member {
  width: 100%;
  height: 50px;
  display: flex;
  justify-items: flex-start;
  padding-left: 10px;
  padding-top: 15px;
  position: fixed;
  z-index: 3;
}

#staffPage .add-member img{
  width: 30px;
  height: 30px;
}

#staffPage .add-member span{
  width: 100px;
  height: 30px;
  line-height: 30px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #1296db;
}

#staffPage .mint-header.is-fixed {
  z-index: 2;
}

#staffPage .member-info, .order-info {
  height: 100%;
  margin-bottom: 55px;
}

#staffPage .mint-tab-container {
  margin-top: 105px;
}

#staffPage .mint-searchbar {
  position: fixed;
  width: 100%;
}

#staffPage .mint-cell-value {
  display: flex;
  justify-content: space-around;
  width: 100%;
  margin-bottom: 1px solid;
}

#staffPage .mint-cell-left, #staffPage .mint-cell-wrapper {
  transform: translate3d(0px, 0px, 0px);
  background-image: none;
}

#staffPage .description {
  display: flex;
  justify-content: space-around;
  width: 100%;
  height: 50px;
  line-height: 50px;
  margin-bottom: 1px solid;
}
</style>
