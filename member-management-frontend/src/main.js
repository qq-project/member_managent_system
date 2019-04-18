// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Mint from 'mint-ui'
import 'mint-ui/lib/style.css'
// 引用vant
import Vant from 'vant'
import 'vant/lib/index.css'

Vue.use(Mint).use(Vant)

Vue.config.productionTip = false

// 判断用户是否登录
router.beforeEach((to, from, next) => {
  // 判断用户是否登录
  if(to.matched.some(record => record.meta.requireAuth)){
    // 需要登录
    const token = localStorage.getItem("token");
    if('unLogin' === token){
      next({
        path:'/login',
      })
    } else {
      next();
    }
  } else {
    next();
  }
})
/* eslint-disable no-new */
new Vue({
  router,
  components: { App },
  template: '<App/>'
}).$mount('#app')
