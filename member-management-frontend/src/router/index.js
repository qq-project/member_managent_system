import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import register from '@/components/register'
import member from '@/components/member'
import notice from '@/components/notice'
import order from '@/components/order'
import products from '@/components/products'
import usercenter from '@/components/usercenter'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/member',
      name: 'member',
      component: member
    },
    {
      path: '/notice',
      name: 'notice',
      component: notice
    },
    {
      path: '/order',
      name: 'order',
      component: order
    },
    {
      path: '/products',
      name: 'products',
      component: products
    },
    {
      path: '/usercenter',
      name: 'usercenter',
      component: usercenter
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    }
  ]
})
