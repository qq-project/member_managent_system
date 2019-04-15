import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import register from '@/components/register'
import member from '@/components/member'
import notice from '@/components/notice'
import products from '@/components/products'
import usercenter from '@/components/usercenter'
import staticRouter from './staticRouter'








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
const router = new Router({
  routes: staticRouter



















































})



export default router
