<template>
  <div id="usercenter">
    <mt-header fixed title="个人中心" style="height:50px"></mt-header>
    <div class="profile">
        <img v-bind:src="userInfo.avatar" alt="头像" style="border-radius: 50%;" @click="detail">
      <div class="middle">
        <p>{{userInfo.nickname}}</p>
        <p>{{userInfo.email}}</p>
      </div>
      <div class="right" @click="updateProfile">
        <img src="../assets/edit.png" alt="编辑" class="edit-img">
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
    <div class="split"></div>
    <mt-cell title="销售管理" to="/sale/saleInfo" is-link>
      <img slot="icon" src="../assets/sale.png" width="24" height="24">
    </mt-cell>
    <div class="split"></div>
    <div @click="logout">
      <mt-cell title="退出登录" >
        <img slot="icon" src="../assets/logout2.png" width="24" height="24">
      </mt-cell>
    </div> 
    <tabbar :selected="selected"></tabbar>

    <van-dialog
      v-model="show"
     show-cancel-button
     title="修改个人信息"
     @confirm="saveUserInfo()"
     @cancel="cancelDialog()"
     :show-confirm-button="isShowConfirmButton"
    >
      <br/>
      <van-uploader :after-read="avartarUpload">
        <!--
        <van-icon name="photograph" size="50px" info="上传头像" ref="uploadAvartar"/>
        -->
      <img :src="userInfo.avatar" style="width:80px;height:80px;border-radius: 50%;" ref="uploadAvartar"/>
      </van-uploader>
      <van-field label="邮箱"  label-align="center" disabled left-icon="envelop-o" v-model="userInfo.email"/>
      <van-field label="昵称" placeholder="请输入姓名"  label-align="center" required left-icon="user-o" v-model="userInfoDialog.nickname" @input="nicknameChange()"/>
      <van-field label="密码"  type="password" placeholder="请输入密码" label-align="center" left-icon="closed-eye" v-model="userInfoDialog.password" @input="passwordChange()"/> 
      <van-field label="确认密码" type="password" placeholder="请输入密码" label-align="center" left-icon="closed-eye" v-model="userInfoDialog.rePassword" @input="rePasswordChange()"/> 
      <van-field label="手机号" placeholder="请输入手机号" label-align="center" left-icon="phone-circle-o" v-model="userInfoDialog.mobile" @input="mobileChange()"/> 
      <br/>
      <van-radio-group class="van-radio" v-model="userInfoDialog.gender">
         &nbsp;&nbsp;&nbsp;
        <label style="color:#323233;font-size:14px">性别</label>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
         <van-radio name="1" style="font-size:14px">男</van-radio>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <van-radio name="2" style="font-size:14px">女</van-radio>
      </van-radio-group>
      <br/>
      <van-field label="地址" placeholder="请输入地址" label-align="center" left-icon="logistics" type="textarea" autosize v-model="userInfoDialog.address" @input="addressChange()"/>
    </van-dialog>
    <!-- 个人详情-->
    <van-dialog
      v-model="detailShow"
      title="个人信息详情"
      :show-confirm-button="isShowConfirmButton2"
      close-on-click-overlay
    >
      <br>
      <img :src="userInfo.avatar" style="width:80px;height:80px;border-radius: 50%;" ref="uploadAvartar"/>
      <van-field label="邮箱"  label-align="center" disabled left-icon="envelop-o" v-model="userInfo.email"/>
      <van-field label="昵称" placeholder="" disabled label-align="center" required left-icon="user-o" v-model="userInfo.nickname" @input="nicknameChange()"/>
      <van-field label="手机号" placeholder=""  disabled label-align="center" left-icon="phone-circle-o" v-model="userInfo.mobile" @input="mobileChange()"/> 
      <br/>
      <van-radio-group class="van-radio" v-model="userInfo.gender">
         &nbsp;&nbsp;&nbsp;
        <label style="color:#323233;font-size:14px" disabled>性别</label>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
         <van-radio name="1" style="font-size:14px">男</van-radio>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <van-radio name="2" style="font-size:14px">女</van-radio>
      </van-radio-group>
      <br/>
      <van-field label="地址" placeholder="请输入地址" disabled label-align="center" left-icon="logistics" type="textarea" autosize v-model="userInfo.address" @input="addressChange()"/>
    </van-dialog>

  </div>

</template>
<script>
import { Search, Cell, Header, Navbar, TabItem, Toast, MessageBox} from 'mint-ui';
import tabbar from './tabbar';
import {userInfoRequest, fileUploadRequest, saveUserInfoRequest, logoutRequest} from '@/api/user'
import {ImageUpload , ImagePreview, UploadFile} from '../lib/index.js'
import { Dialog } from 'vant';
  export default{
    components: {
      tabbar,
      'image-upload':ImageUpload,
      'image-preview':ImagePreview,
      'upload':UploadFile,
      //'vanDialog': Dialog,
      },
    data(){
      return {
        selected: 'usercenter',
        userInfo: {
          nickname: '',
          email: '',
          mobile:'',
          address:'',
          avatar: require('../assets/user.png'),
          id:''// 便于修改
        },
        isUploadFile:false,
        show:false,
        isShowConfirmButton:true,
        detailShow:false,
        isShowConfirmButton2:false,
        userInfoDialog:{
           nickname: '',
           email: '',
           avatar: require('../assets/user.png'),
           gender:'',
           password:'',
           rePassword:'',
           address:'',
           mobile:''
        }
      }
    },
    mounted () {
      userInfoRequest().then(data => {
        const {resCode, resMsg, result} = data;
        if('000000' != resCode){
          Toast(resMsg);
          return;
        }
        this.initProfile(result);
      })
    },
    methods: {
      // 初始化数据
      initProfile(result) {
        const {nickname, email, imgSrc, mobile, gender, address, id} = result;
        // TODO: 查询用户个人信息，并初始化
        this.userInfo.id = id;
        this.userInfo.nickname = nickname || '大裴女侠';
        this.userInfo.email = email || '1421865369@qq.com';
        this.userInfo.mobile = mobile;
        this.userInfo.address = address;
        this.userInfo.gender = gender;
        this.userInfoDialog.nickname = nickname || '大裴女侠';
        
        // 初始化 userInfoDialog
        if(null == imgSrc || '' === imgSrc){
          this.isUploadFile = true;
        }

        this.userInfo.avatar = imgSrc;
      },
      // 显示Dialog
      updateProfile() {
        //  TODO: 进入更新信息页面 调用 update profile 的 api
        this.show = true
      },
      // 保存用户修改
      saveUserInfo(){
        // 根据ID进行修改
        this.userInfoDialog.id = this.userInfo.id;
        // 此处是dialog修改后的数据
        saveUserInfoRequest(this.userInfoDialog).then(data => {
          // 注意，当用户修改密码时，强制登出

        })         
      },
      // 关闭dialog
      cancelDialog(){
        userInfoRequest().then(data => {
          const {resCode, resMsg, result} = data;
          if('000000' != resCode){
            Toast(resMsg);
            return;
          }
          this.initProfile(result);
        })
        // 重新初始化
        this.userInfoDialog = {
           nickname: this.userInfo.nickname,
           email: '',
           avatar:'',
           gender:'',
           password:'',
           rePassword:'',
           address:'',
           mobile:''
        }
      },
      // 校验昵称
      nicknameChange(){
        // 校验姓名
        const {nickname} = this.userInfoDialog;
        if(null == nickname || '' === nickname){
          this.$notify('昵称不能为空')
          this.isShowConfirmButton = false;
          return;
        }
        if(!/^[\u4e00-\u9fa5]{1,10}$/.test(nickname)){
          this.$notify('昵称只支持10以内中文字符')
          this.isShowConfirmButton = false;
          return;
        }
        this.isShowConfirmButton = true;
      },
      // 校验密码
      passwordChange(){
        const {password} = this.userInfoDialog;
        if(null == password || '' === password || password.trim() === ''){
          this.$notify('密码不能为空')
          this.isShowConfirmButton = false;
          return;
        }
        if(/^(\w){6,20}$/.test(password)){
          this.$notify('密码只能输入6-20个字母、数字、下划线')
          this.isShowConfirmButton = false;
          return;
        }
        this.isShowConfirmButton = true;
      },
      // 校验确认密码
      rePasswordChange(){
        const {password, rePassword} = this.userInfoDialog;
        if(null == password || '' === password || password.trim() === ''){
          this.$notify('密码不能为空')
          this.isShowConfirmButton = false;
          return;
        }
        if(password !== rePassword){
          this.$notify('两次密码输入不一致');
          this.isShowConfirmButton = false;
          return;
        }
        this.isShowConfirmButton = true;
      },
      // 校验地址
      addressChange(){
        const {address} = this.userInfoDialog;
        if(null == address || '' === address){
          this.$notify('请输入地址');
          this.isShowConfirmButton = false;
          return;
        }
         this.isShowConfirmButton = true;
      },
      // 校验手机号
      mobileChange(){
        const {mobile} = this.userInfoDialog;
        if(null == mobile || '' === mobile){
          this.$notify('请输入手机号');
          this.isShowConfirmButton = false;
          return;
        }
        if(!/^1[34578]\d{9}$/.test(mobile)){
          this.$notify('请输入正确的手机号');
          this.isShowConfirmButton = false;
          return;
        }
        this.isShowConfirmButton = true;
      },
      // 头像处理
      avartarUpload(file, detail){
          if(undefined == file ){
            Toast.fail('请选择图片')
            return;
          }
           // 文件上传 操作
          fileUploadRequest(file.file, 'api/upload/userAvartarUpload', 'avartar'+this.userInfo.email).then(data => {
            // 处理响应后
            const {resCode, resMsg, result} = data;
            this.isShowConfirmButton = false;
            if('000000' !== resCode){
              Toast.fail('上传图片失败，失败原因：'+resMsg);  
              this.isShowConfirmButton = true;
              // 将图片显示为上传的图片
              return;
          }
            // 上传成功
            this.isShowConfirmButton = true;
            this.avatar = result.imgSrc;
            this.$refs.uploadAvartar.src = result.imgSrc;
            this.$notify({
              message:'上传头像成功',
              background:'green'
            })
          }).catch(err => {
            this.isUploadFile = false;
          });
      },
      // 查看个人详情
      detail(){
        this.detailShow = true;

      },
      // 退出登录操作
      logout(){
        // 询问用户是否确认退出
        Dialog.confirm({
          title:'退出',
          message:'确认退出？'
        }).then(() => {
          logoutRequest().then(() => {
            Toast('退出成功！');
            // 登出标志，避免报错
            localStorage.setItem('token','unLogin');
            this.$router.push({
              path: '/login'
            });
          });
        }).catch(()=>{
          
        })
      }
    },
    
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
  margin-top:20px
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
/* radio flex布局*/
.van-radio {
  display: flex;
  
  align-items:center;
}

</style>
