<template>
  <div id="usercenter">
    <mt-header fixed title="个人中心"></mt-header>
    <div class="profile">
      <img v-bind:src="userInfo.avatar" alt="头像">
      <div class="middle">
        <p>{{userInfo.username}}</p>
        <p>{{userInfo.email}}</p>
      </div>
      <div class="right" @click="updateProfile">
        <img src="../assets/edit.png" alt="编辑">
        <img src="../assets/right.png" alt="进入">
      </div>
    </div>
    <div class="split"></div>
    <mt-cell title="积分配置" to="/points" is-link>
      <img slot="icon" src="../assets/points.png" width="24" height="24">
    </mt-cell>
    <div class="split"></div>
    <mt-cell title="员工管理" to="/staffs" is-link>
      <img slot="icon" src="../assets/staffs.png" width="24" height="24">
    </mt-cell>
    <div class="split"></div>
    <mt-cell title="推广管理" to="/notice" is-link>
      <img slot="icon" src="../assets/notice.png" width="24" height="24">
    </mt-cell> 
    <tabbar :selected="selected"></tabbar>
  </div>

</template>
<script>
import { Search, Cell, Header, Navbar, TabItem, Toast } from 'mint-ui';
import tabbar from './tabbar';
  export default{
    components: {tabbar},
    data(){
      return {
        selected: 'usercenter',
        userInfo: {
          username: '',
          email: '',
          avatar: require('../assets/user.png')
        },
      }
    },
    mounted () {
      this.initProfile()
    },
    methods: {
      initProfile() {
        // TODO: 查询用户个人信息，并初始化
        this.userInfo.username = '大裴女侠';
        this.userInfo.email = '1421865369@qq.com';
        this.userInfo.avatar = require('../assets/user.png');
      },
      updateProfile() {
        //  TODO: 进入更新信息页面 调用 update profile 的 api
        Toast("更新信息成功！");
      }
    }
  }
</script>
<style>
#usercenter {
  width: 100%;
  height: 100%;
  background-color: #e8e8e8;
}

#usercenter .split {
  width: 100%;
  height: 10px;
  background-color: #e8e8e8;
}

#usercenter .mint-cell-title {
  flex: none;
}

#usercenter .mint-cell-text {
  padding-left: 20px;
}

#usercenter .profile {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  height: 30%;
  background-color: #fff;
}

#usercenter .profile img {
  width: 80px;
  height: 80px;
}

#usercenter .profile .middle {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  width: 180px;
  height: 80px;
}

#usercenter .profile .middle p:first-child{
  font-size: 25px;
  font-weight: bold;
}

#usercenter .profile .right {
  position: relative;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-end;
  width: 70px;
  height: 80px;
}

#usercenter .profile .right img:first-child {
  position: absolute;
  width: 40px;
  height: 40px;
  bottom: -10px;
}

#usercenter .profile .right img:nth-child(2) {
  position: absolute;
  right: 10px;
  width: 20px;
  height: 20px;
}
</style>
