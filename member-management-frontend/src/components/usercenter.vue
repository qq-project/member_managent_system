<template>
  <div id="usercenter">
    <mt-header fixed title="个人中心"></mt-header>
    <div class="profile">
      <div v-show="!isUploadFile">
        <img v-bind:src="userInfo.avatar" alt="头像" @click="modifiedAvartar">
      </div>
      <div v-show="isUploadFile">     
        <upload :multiple="false" :max=1 ref="upload" @click="uploadAvartar()"/>
        <mt-button type="primary" size="small" style="margin-left:-10px" @click="uploadAvartar()">上传</mt-button>
      </div>
      <div class="middle">
        <p>{{userInfo.nickname}}</p>
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

    <van-dialog
      v-model="show"
     show-cancel-button
     title="修改个人信息"
    >
      <van-field label="邮箱" value="1028438733@qq.com" size="large" label-align="center" disabled/>
      <van-field label="昵称" placeholder="请输入姓名" size="large" label-align="center" required/>
      <van-field label="密码" placeholder="请输入密码" size="large" label-align="center" /> 
      <van-field label="确认密码" placeholder="请输入密码" size="large" label-align="center"/> 
      <van-field label="手机号" placeholder="请输入手机号" size="large" label-align="center"/> 
    </van-dialog>
  </div>

</template>
<script>
import { Search, Cell, Header, Navbar, TabItem, Toast, MessageBox} from 'mint-ui';
import tabbar from './tabbar';
import {userInfoRequest, fileUploadRequest} from '@/api/user'
import {ImageUpload , ImagePreview, UploadFile} from '../lib/index.js'
//import {Dialog} from 'vant'
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
          avatar: require('../assets/user.png')
        },
        isUploadFile:false,
        show:false,
        is:true
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
      initProfile(result) {
        const {nickname, email, imgSrc} = result;
        // TODO: 查询用户个人信息，并初始化
        this.userInfo.nickname = nickname || '大裴女侠';
        this.userInfo.email = email || '1421865369@qq.com';
        // 当用户未上传头像时，显示上传组件
        if(null == imgSrc || '' === imgSrc){
          this.isUploadFile = true;
        }

        this.userInfo.avatar = imgSrc;
      },
      updateProfile() {
        //  TODO: 进入更新信息页面 调用 update profile 的 api
        this.show = true
      },
      // 头像上传
      uploadAvartar(){
        // 这里提示一下
          MessageBox.confirm('确认更换头像？').then(action =>{
           // 获取子组件文件
          const {file} = this.$refs.upload;
          console.dir(file)
          if(undefined == file || undefined == file.size || 0 == file.size){
            MessageBox.alert('请选择文件！');
            return;
          }
          // 文件上传 操作
          fileUploadRequest(file, 'api/upload/userAvartarUpload', 'avartar').then(data => {
            // 处理响应后
            const {resCode, resMsg, result} = data;
            console.log(data);
            console.log(resCode);
            if('000000' !== resCode){
              MessageBox.alert(resMsg);
              return;
            }
            // 上传成功
            this.isUploadFile = false;
            this.avatar = result.imgSrc;
            Toast('上传头像成功');
          })
        }).catch(err => {
            this.isUploadFile = false;
          });
      },
      // 修改头像
      modifiedAvartar: function(){
        MessageBox.confirm('确认上传头像？').then(action =>{
          // 把上传组件显示出来
          this.isUploadFile = true;
        }).catch(err => {
          // 取消回调
          this.isUploadFile = false;
        });
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


.image_upload{  height: 60px; width: 60px;background: red}

</style>
