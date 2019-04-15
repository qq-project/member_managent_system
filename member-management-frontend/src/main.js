// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Mint from 'mint-ui'
import TuaStorage from 'tua-storage'
import 'mint-ui/lib/style.css'

Vue.use(Mint)
Vue.use(TuaStorage, {
  storageEngine: window.localStorage
})

Vue.config.productionTip = false

// 判断用户是否登录
router.beforeEach((to, from, next) => {
  console.dir(to.meta);
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
