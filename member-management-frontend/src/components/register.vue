<template>
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
    <div>
    </div>
    <mt-button type="primary" class="bit-login" @click='sendRegister'>注册</mt-button>
    <router-link to='/login' class="register" style='text-decoration:none;'>
      <span style="color:#707472">立即登录</span></router-link>
  </div>
</template>

<script>
import {Toast, MessageBox} from 'mint-ui';
import {registerRequest} from '@/api/user'
export default {
  data () {
    return {
      registerForm:{
        email:'',
        nickname: '',
        password: '',
        secondPassword: '',
        mobile: '',
        gender:'1',
        address:''
     },
      gender_options:[
          {label:'男',value:'1'},
          {label:'女',value:'2'},
      ],
    }   
  },
  mounted () {
  },
  methods: {
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

<style lang='scss' scoped>
.register {
  padding: 10px;
  .register-header{
    height: 42px;
    background: #0094ff;
    font-size: 18px;
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  }
  .reg-center{
    margin-top: 60px;
  }
  .kuan {
    border: 1px solid #ccc;
    margin-bottom: 10px;
    border-radius: 5px;
    min-height: 35px;
  }
  .bit-login {
    width: 100%;
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

</style>
