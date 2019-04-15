<template>
  <div class="main">
    <div class="title">
        <span>注册用户</span>
  <div class="register">
    <mt-header fixed title="用户注册" class="register-header"></mt-header>
    <div class="reg-center">
      <mt-field placeholder="登录邮箱"  v-model="registerForm.email" class="kuan"></mt-field>
      <mt-field placeholder="昵称" v-model="registerForm.nickname" class="kuan"></mt-field>
      <mt-field placeholder="密码" type="password" v-model="registerForm.password" class="kuan"></mt-field>
      <mt-field placeholder="确认密码" type="password" v-model="registerForm.secondPassword" class="kuan"></mt-field>
      <mt-field placeholder="手机号" v-model="registerForm.mobile" class="kuan"></mt-field>
      
      <mt-field placeholder="地址" v-model="registerForm.address" class="kuan"></mt-field>
      <mt-radio v-model="registerForm.gender" :options="gender_options" id="gender" align="left" class="gender-radio"></mt-radio>
    </div>

    <div class="title-msg">
        <!-- <span>请输入必需的注册信息</span> -->
    <div>
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
    <mt-button type="primary" class="bit-login" @click='sendRegister'>注册</mt-button>
    <router-link to='/login' class="register" style='text-decoration:none;'>
      <span style="color:#707472">立即登录</span></router-link>
  </div>
        <div class="login" @click="toLogin">登录</div>
</template>

    <!-- </form> -->
  
  </div>
</template>
<script>
  export default{
    data(){
import {Toast, MessageBox} from 'mint-ui';
import {registerRequest} from '@/api/user'
export default {
  data () {
    return {
          username: '',
          email: '',
      registerForm:{
        email:'',
        nickname: '',
        password: '',
          checkpassword: '',
          phone: '',
          birthday: '',
          options: [
              {lable: '男', value: '0'},
              {lable: '女', value: '1'}
        secondPassword: '',
        mobile: '',
        gender:'1',
        address:''
     },
      gender_options:[
          {label:'男',value:'1'},
          {label:'女',value:'2'},
      ],
          gender: '0',
          address: '',
          attr: {required: true}
      }
    }   
  },
  mounted () {
  },
  methods: {
        toLogin: function () {
            var _this = this;
            _this.$router.push({path: '/login'})
        },
        confirmRegister: function() {
            var _this = this;
            _this.$router.push({path: '/register'})
    sendRegister () {
      // 参数校验
      if(null == this.registerForm){
        Toast('请输入参数');
        return;
      }
      const {email, nickname, password, secondPassword, mobile, gender, address} = this.registerForm;
      if(null == email || '' === email){
        Toast('请输入登录邮箱');
        return;
      }
      // 正则校验邮箱
      var email_reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      /*
      if(!email_reg.test(email)){
        Toast('请输入有效的邮箱');
        return;
      }
      */
      if(null == nickname || '' === nickname){
        Toast('请输入昵称');
        return;
      }
      if(null == password || '' === password){
        Toast('请输入登录密码');
        return;
      }
      // 正则校验密码，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
      var password_reg = /^(\w){6,20}$/;
      if(!password_reg.test(password)){
        Toast('密码只能输入6-20个字母、数字、下划线');
        return;
      }
      if(null == secondPassword || '' === secondPassword){
        Toast('请确认密码');
        return;
      }
      if(password !== secondPassword){
        Toast('两次密码输入不一致');
        return;
      }
      if(null == mobile || '' === mobile){
        Toast('请输入手机号');
        return;
      }
      var mobile_reg = /^1[34578]\d{9}$/;
      if(!mobile_reg.test(mobile)){
        Toast('手机号码有误，请重新输入');
        return;
      }
      // 正则校验手机号
      if(null == address || '' === address){
        Toast('请输入地址');
        return;
      }
      // 校验通过，请求后台  
      registerRequest(this.registerForm).then(data => {
        if('000000' !== data.resCode){
          MessageBox.alert(data.resMsg);
          return;
        }
        Toast('注册成功!');
      })
    },
  }
}
</script>
<style>
.main {
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
<style lang='scss' scoped>
.register {
  padding: 10px;
  .register-header{
    height: 42px;
    background: #0094ff;
    font-size: 18px;
    color: #353f42;
}

.title-msg {
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  }
  .reg-center{
    margin-top: 30px;
  }
  .kuan {
    border: 1px solid #ccc;
    margin-bottom: 10px;
    border-radius: 5px;
    min-height: 35px;
  }
  .bit-login {
    width: 100%;
    height: 64px;
    line-height: 64px;
    background: #0094ff
  }
  .register {
    color: #0094ff;
    float: right;
    font-size: 14px;
    margin-top: 15px;
    padding: 5px 2px;
  }
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
