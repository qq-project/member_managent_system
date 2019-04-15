<template>

  <div class="login">
		<mt-header fixed title="用户登录" class="login-header"></mt-header>
		<div class="div-center">
			<mt-field placeholder="用户名/手机号" v-model="loginForm.username" class="kuan"></mt-field>
			<mt-field placeholder="密码" type="password" v-model="loginForm.password" class="kuan"></mt-field>
			<mt-button type="primary" class="bit-login" @click='submitClick'>登录</mt-button>
		</div>
    <div class="foor">
        <div class="left"><span>忘记密码 ?</span></div>

        <div @click="toRegister" class="right"><span>注册账户</span></div>
    </div>
  </div>
</template>

<script>
import { Toast, MessageBox } from 'mint-ui'
import {loginRequest} from '@/api/user'
export default {
  data () {
    return {
				loginForm: {
					username: 'chushen',
					password: '123'
				},
			}
  },
  mounted () {
  },
  methods: {
			toRegister: function() {
				var _this = this;
				console.log("das")
				_this.$router.push({
					path: '/register'
				})
			},
			submitClick: function() {
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
				// 登录请求
				loginRequest(this.loginForm).then(data => {
					if (null == data) {
						MessageBox.alert("请求失败");
						return
					}
					const {
						resCode,
						resMsg,
						result
					} = data;
					if ('000000' !== resCode) {
						MessageBox.alert(resMsg);
						return;
					}
					// 登录成功
					Toast('登录成功，欢迎');
					localStorage.removeItem('token');
					_this.$router.push({
						path: '/member'
					});
				})
				//


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

<style lang='scss' scoped>
.login {
  padding: 10px;
	.div-center {
		margin-top: 60px;
	}
  .kuan {
    border: 1px solid #ccc;
    margin-bottom: 10px;
    border-radius: 5px;
    min-height: 40px;
  }
  
  .bit-login {
    width: 100%;
    margin-top: 25px;
    background: #0094ff
  }
  .register {
    color: #0094ff;
    float: right;
    font-size: 14px;
    margin-top: 15px;
    padding: 5px 2px;
  }
	.login-header{
		height: 42px;
		background: #0094ff;
		font-size: 18px;
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
	}
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
.foor div:hover {
  cursor:pointer;
  color: #484847;
  font-weight: 600;
}
</style>
