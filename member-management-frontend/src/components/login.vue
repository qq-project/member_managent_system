<template>
  <div class="loginPage">
    <div class="title">
        <span>密码登录</span>
    </div>

    <div class="title-msg">
        <span>请输入登录账户和密码</span>
    </div>

    <!--输入框-->
    <div class="input-content">
        <!--autoFocus-->
        <div>
            <input type="text" autocomplete="off" v-model="loginForm.username" placeholder="用户名" name="userNameOrEmailAddress" />
        </div>

        <div style="margin-top: 16px">
            <input type="password" autocomplete="off" v-model="loginForm.password" placeholder="登录密码" name="password" maxlength="32"/>
        </div>
    </div>

    <!--登入按钮-->
    <div style="text-align: center">
        <button type="submit" @click="submitClick" class="enter-btn" >登录</button>
    </div>

    <div class="foor">
        <div class="left"><span>忘记密码 ?</span></div>

        <div @click="toRegister" class="right"><span>注册账户</span></div>
    </div>

  
  </div>
</template>
<script>
import {Toast} from 'mint-ui';
  export default{
    data(){
      return {
        checked: true,
        loginForm: {
          username: '',
          password: ''
        },
        loading: false
      }
    },
    methods: {
      toRegister: function () {
        var _this = this;
        console.log("das")
        _this.$router.push({path: '/register'})
      },
      submitClick: function () {
        var _this = this;
        this.loading = true;
        
        var name = this.loginForm.username;
        var password = this.loginForm.password;

        if (name == '' || name == null) {
          Toast('请输入用户名');
          return;
        } else if (password == '' || password == null) {
          Toast('请输入密码');
          return;
        }

        _this.$router.push({path: '/member'})


        // this.postRequest('/login', {
        //   username: this.loginForm.username,
        //   password: this.loginForm.password
        // }).then(resp=> {
        //   _this.loading = false;
        //   if (resp && resp.status == 200) {
        //     var data = resp.data;
        //     _this.$store.commit('login', data.obj);
        //     var path = _this.$route.query.redirect;
        //     _this.$router
        //       .replace({path: path == '/' || path == undefined ? '/home' : path});
        //   }
        // });
      }
    }
  }
</script>
<style>
.loginPage {
  margin: 0 auto;
  width: 100%;
  background: #FFFFFF;
}

.title {
  width: 100%;
  height: 40px;
  line-height: 40px;
}

.title span {
  font-size: 18px;
  color: #353f42;
}

.title-msg {
  width: 100%;
  height: 64px;
  line-height: 64px;
}

.title:hover{
  cursor: default;
}

.title-msg:hover{
  cursor: default;
}

.title-msg span {
  font-size: 12px;
  color: #707472;
}

.input-content {
  width: 100%;
  height: 120px;
}

.input-content input {
  width: 330px;
  height: 40px;
  border: 1px solid #dad9d6;
  background: #ffffff;
  padding-left: 10px;
  padding-right: 10px;
}

.enter-btn {
  width: 350px;
  height: 40px;
  color: #fff;
  background: #0bc5de;
  line-height: 40px;
  text-align: center;
  border: 0px;
}

.foor{
  width: 90%;
  height: auto;
  color: #9b9c98;
  font-size: 12px;
  margin-top: 20px;
  display: flex;
  margin-left: 5%;
  justify-content: space-between;
}

.enter-btn:hover {
  cursor:pointer;
  background: #1db5c9;
}

.foor div:hover {
  cursor:pointer;
  color: #484847;
  font-weight: 600;
}
</style>
