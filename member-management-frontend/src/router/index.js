import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import register from '@/components/register'
import member from '@/components/member'
import notice from '@/components/notice'
import points from '@/components/points'
import order from '@/components/order'
import products from '@/components/products'
import usercenter from '@/components/usercenter'
import staffs from '@/components/staffs'
import userInfo from '@/components/userInfo'

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
      path: '/points',
      name: 'points',
      component: points
    },
    {
      path: '/staffs',
      name: 'staffs',
      component: staffs
    },
    {
      path: '/userInfo',
      name: 'userInfo',
      component: userInfo
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
