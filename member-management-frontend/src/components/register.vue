<template>
  <div id="registerPage">
    <div class="title">
        <span>注册用户</span>
    </div>

    <div class="title-msg">
        <!-- <span>请输入必需的注册信息</span> -->
    </div>

    <!-- <form class="register-form" method="post" novalidate > -->
        <!--输入框-->
        <div class="input-content">
            <mt-field :attr="attr" label="用户昵称" placeholder="请输入用户昵称" v-model="username"></mt-field>
            <span class="gender-span">性别</span>
            <mt-radio
            v-model="gender"
            :options="['男', '女']">
            </mt-radio>
            <mt-field label="邮箱" placeholder="登录使用，不可修改" type="email" v-model="email"></mt-field>
            <mt-field label="密码" placeholder="请输入密码" type="password" v-model="password"></mt-field>
            <mt-field label="确认密码" placeholder="请再次输入密码" type="checkpassword" v-model="password"></mt-field>
            <mt-field label="手机号" placeholder="请输入手机号" type="tel" v-model="phone"></mt-field>
            <mt-field label="生日" placeholder="请输入生日" type="date" v-model="birthday"></mt-field>
            <mt-field label="地址" placeholder="请输入地址" type="textarea" rows="3" v-model="address"></mt-field>
            <button type="submit" class="enter-btn" @click="confirmRegister">确 认 注 册</button>
        </div>
        <div class="login" @click="toLogin">登录</div>

    <!-- </form> -->
  
  </div>
</template>
<script>
  export default{
    data(){
      return {
          username: '',
          email: '',
          password: '',
          checkpassword: '',
          phone: '',
          birthday: '',
          options: [
              {lable: '男', value: '0'},
              {lable: '女', value: '1'}
          ],
          gender: '0',
          address: '',
          attr: {required: true}
      }
    },
    methods: {
        toLogin: function () {
            var _this = this;
            _this.$router.push({path: '/login'})
        },
        confirmRegister: function() {
            var _this = this;
            _this.$router.push({path: '/register'})
        }
    }
  }
</script>
<style>
#registerPage {
    margin: 0 auto;
    width: 100%;
    height: 100%;
    background: #FFFFFF;
}

.login {
    background-color: #fff;
    width: 60px;
    border: none;
    color: #96cdcd;
    float: right;
}

.register-form {
    height: 100%;
    width: 100%;
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
    cursor: default	;
}

.title-msg:hover{
    cursor: default	;
}

.title-msg span {
    font-size: 12px;
    color: #707472;
}

.input-content {
    width: 100%;
    height: 100%;
}

.input-content input {
    height: 40px;
    border: 1px solid #dad9d6;
    background: #ffffff;
    padding-left: 10px;
    padding-right: 10px;
}

.enter-btn {
    height: 40px;
    color: #fff;
    background: #0bc5de;
    line-height: 40px;
    text-align: center;
    border: 0px;
    width: 50%;
    margin-top: 10%;
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

.gender-span {
    display: inline-block;
    line-height: 48px;
    min-height: 48px;
    position: relative;
    overflow: hidden;
    margin-left: -60px;
}

.mint-radiolist, .mint-radiolist .mint-cell{
    display: inline-block;
}

.mint-radiolist-title {
    margin: 0px;
}

.mint-cell-wrapper,.mint-cell:last-child {
    background-size: 0px;
}
</style>
