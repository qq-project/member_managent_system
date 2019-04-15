/**
 * 记录一些常用方法
 */

 /**
  * 设置storage过期时间
  */
localStorage.prototype.setExpire = function(key, value, expire){
    let obj = {
        data:value,
        time:new Date(),
        expire:expire
    };
    localStorage.setItem(key, JSON.stringify(obj));
}

/**
 * 获取localstorage 存储的内容
 */
localStorage.prototype.getExpire =function(key) {
    let val = localStorage.getItem(key);
    if(!val){
        return val;
    }
    val = JSON.parse(val);
    if(Date.now() - val.time > expire){
        localStorage.removeItem(key);
        return null;
    }
    return val.data;
}
