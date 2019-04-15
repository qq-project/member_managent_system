import login from '@/components/login'
import register from '@/components/register'
import member from '@/components/member'
import notice from '@/components/notice'
import products from '@/components/products'
import usercenter from '@/components/usercenter'
  const staticRouter= [
    {
      // 默认路径是member
      path: '/',
      name: 'member',
      component: member,
      meta:{
        title:'会员管理页',
        requireAuth:true //判断是否需要登录，此处需要
      }
    },
    {
      path: '/member',
      name: 'member',
      component: member,
      meta:{
        title:'会员管理页',
        requireAuth:true //判断是否需要登录，此处需要
      }
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
      component: login,
      meta:{
        title:'login',
        requireAuth:false //判断是否需要登录，此处不需要
      }
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
  ]
export default staticRouter