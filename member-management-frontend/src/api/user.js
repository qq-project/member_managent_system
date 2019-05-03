/**
 * 该文件主要是封装用户的请求
 */

import {postRequest, getRequest, uploadRequest} from '../utils/request'

/**
 * 登录请求
 * @param {*} params 
 */
export const loginRequest = params => {
    return postRequest('/api/user/login', params).then(data => {
        return data;
    });
}

/**
 * 
 * @param {注册} params 
 */
export const registerRequest = params => {
    return postRequest('/api/register/userRegister', params).then(data => {
        return data;
    });
}

/**
 *  请求会员信息列表
 * @param {*} params 
 */
export const memberInfoListRequest = params => {
    return postRequest('/api/memberInfo/memberInfoList', params).then(data => {
        return data;
    });
}

/**
 * 更新会员信息
 * @param {*} params 
 */
export const updateMemberInfoRequest = (params) => {
    return postRequest('/api/memberInfo/updateMemberInfo', params).then(data => {
        return data;
    });
}

/**
 * 保存会员信息
 * @param {*} params 
 */
export const saveMemberInfoRequest = (params) => {
    return postRequest('/api/memberInfo/saveMemberInfo', params).then(data =>{
        return data;
    })
}

/**
 * 删除会员
 * @param {*} params 
 */
export const deleteMemberInfoRequest = (params) =>{
    postRequest('/api/memberInfo/deleteMemberInfo', params).then(data => {
        return data;
    })
}

/**
 * 查询当前用户信息 
 */
export const userInfoRequest =() => {
    // 请求当前登录用户
    return postRequest('api/user/queryUserInfo', "").then(data =>{
        return data;
    });
}

/**
 * 更新当前用户信息
 * @param {*} params 
 */
export const saveUserInfoRequest = (params) =>{
    // 更新当前用户信息，如果密码修改需要重新登录
    return postRequest('api/user/saveUserInfo', params).then(data =>{
        return data;
    });
}

/**
 * 查询商品列表
 * @param {*} params 
 */
export const productInfoListRequest = (params) => {
    return postRequest('/api/productInfo/productInfoList', params).then(data => {
        return data;
    })
}

/**
 * 修改商品信息
 * @param {*} params 
 */
export const updateProductInfoRequest = (params) => {
    return postRequest('/api/productInfo/updateProductInfo', params).then(data => {
        return data;
    }) 
}

/**
 * 新增商品信息
 * @param {*} params 
 */
export const saveProductInfoRequest = (params) => {
    return postRequest('/api/productInfo/saveProductInfo', params).then(data => {
        return data;
    }) 
}

/**
 * 删除商品信息
 * @param {*} params 
 */
export const deleteProductInfoRequest = (params) => {
    return postRequest('/api/productInfo/deleteProductInfo', params).then(data => {
        return data;
    }) 
}

/**
 * 查询类别列表
 * @param {*} params 
 */
export const categoryInfoListRequest = (params) => {
    return postRequest('/api/categoryInfo/categoryInfoList', params).then(data => {
        return data;
    })
}

/**
 * 新增商品类别信息
 * @param {*} params 
 */
export const addCategoryInfoRequest = (params) => {
    return postRequest('/api/categoryInfo/addCategoryInfo', params).then(data => {
        return data;
    })
}

/**
 * 创建订单
 * @param {*} params 
 */
export const createOrderRequest = (params) => {
    return postRequest('/api/order/createOrder', params).then(data => {
        return data;
    })
}

/**
 * 查询会员订单列表
 * @param {*} params 
 */
export const memberOrderListRequest = (params) => {
    return postRequest('/api/memberOrder/queryMemberOrderList', params).then(data => {
        return data;
    })
}

/**
 * 会员订单提交(下单)
 * @param {*} params 
 */
export const memberOrderSubmitRequest = (params) => {
    return postRequest('/api/memberOrder/memberOrderSubmit', params).then(data => {
        return data;
    })
}

/**
 * 删除购物车订单
 * @param {*} params 
 */
export const cartOrderDelRequest = (params) => {
    return postRequest('/api/order/cartOrderDel', params).then(data => {
        return data;
    })
}

/**
 * 查询积分配置信息列表
 * @param {*} params 
 */
export const integralConfigListRequest = (params) => {
    return postRequest('/api/integralConfig/queryIntegralList', params).then(data => {
        return data;
    })
}

/**
 * 更新积分配置信息
 * @param {*} params 
 */
export const updateIntegralConfigRequest = (params) => {
    return postRequest('/api/integralConfig/updateIntegralConfig', params).then(data => {
        return data;
    })
}

/**
 * 获取当前积分配置表最大的范围
 * @param {*} params 
 */
export const getIntegralMaxAmountRequest = (params) => {
    return postRequest('/api/integralConfig/getIntegralMaxAmount', params).then(data => {
        return data;
    })
}

/**
 * 新增积分配置信息
 * @param {*} params 
 */
export const saveIntegralConfigRequest = (params) => {
    return postRequest('/api/integralConfig/saveIntegralConfig', params).then(data => {
        return data;
    })
}

/**
 * 查询员工列表
 * @param {*} params 
 */
export const userListRequest = (params) => {
    return postRequest('/api/userInfo/userList', params).then(data => {
        return data;
    })
}

/**
 * 查询用户详情(查询数据库)
 * @param {*} params 
 */
export const userDetailRequest = (params) => {
    return postRequest('/api/userInfo/userDetail', params).then(data => {
        return data;
    })
}

/**
 * 用户信息编辑
 * @param {*} params 
 */
export const userUpdateRequest = (params) => {
    return postRequest('/api/userInfo/updateUserInfo', params).then(data => {
        return data;
    })
}

/**
 * 删除用户信息
 * @param {*} params 
 */
export const userDelRequest = (params) => {
    return postRequest('/api/userInfo/userDel', params).then(data => {
        return data;
    })
}

/**
 * 商品销售情况查询
 * @param {*} params 
 */
export const saleInfoProductRequest = (params) => {
    return postRequest('/api/saleInfo/saleInfoProduct', params).then(data => {
        return data;
    })
}

/**
 * h会员订单
 * @param {*} params 
 */
export const orderInfoProductRequest = (params) => {
    return postRequest('/api/saleInfo/orderInfoProduct', params).then(data => {
        return data;
    })
}

/**
 * 订单(天)情况查询
 * @param {*} params 
 */
export const orderInfoDayRequest = (params) => {
    return postRequest('/api/saleInfo/orderInfoDay', params).then(data => {
        return data;
    })
}



/**
 *  登出
 * @param {*} params 
 */
export const logoutRequest = () => {
    return getRequest('/api/user/logout')
}

/**
 * 文件上传请求
 * params 文件内容
 * url 当前路径
 * 目前支持单文件上传
 */
export const fileUploadRequest=(file, url, filename) => {
    const params = new FormData();
    params.append(filename, file);
    return uploadRequest(url, params).then(data =>{
        return data;
    });
}