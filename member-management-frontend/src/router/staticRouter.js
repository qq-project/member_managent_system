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
      component: notice,
      meta:{
        title:'通知页',
        requireAuth:true //判断是否需要登录，此处需要
      }
    },
  
    {
      path: '/points',
      name: 'points',
      component: points,
      meta:{
        title:'会员管理页',
        requireAuth:true //判断是否需要登录，此处需要
      }
    },
    {
      path: '/staffs',
      name: 'staffs',
      component: staffs,
      meta:{
        title:'会员管理页',
        requireAuth:true //判断是否需要登录，此处需要
      }
    },
    {
      path: '/userInfo',
      name: 'userInfo',
      component: userInfo,
      meta:{
        title:'用户信息页',
        requireAuth:true //判断是否需要登录，此处需要
      }
    },
    {
      path: '/order',
      name: 'order',
      component: order,
      meta:{
        title:'订单管理页',
        requireAuth:true //判断是否需要登录，此处需要
      }
    },
    {
      path: '/products',
      name: 'products',
      component: products,
      meta:{
        title:'商品管理页',
        requireAuth:true //判断是否需要登录，此处需要
      }
    },
    {
      path: '/usercenter',
      name: 'usercenter',
      component: usercenter,
      meta:{
        title:'用户中心页',
        requireAuth:true //判断是否需要登录，此处需要
      }
      
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
      component: register,
      meta:{
        title:'会员管理页',
        requireAuth:false //判断是否需要登录，此处需要
      }
    },
  ]
export default staticRouter