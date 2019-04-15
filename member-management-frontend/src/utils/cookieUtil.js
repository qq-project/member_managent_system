/**
 * cookie工具方法
 * 
 */

 export default {
     /**
      * 设置cookie
      */
    setCookie:(name, value, days) => {
        var d = new Date();
        d.setTime(d.getTime() + 24*60*60*1000*days);
        window.document.cookie = name + "=" + value + ";path=/;expires=" + d.toGMTString();
    },
    /**
     * 获取cookie
     */
    getCookie: name =>{
        var v = window.document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
        return v ? v[2] : null;
    },
    /**
     * 删除cookie
     */
    delCookie: name =>{
        this.setCookie(name, '', -1); //将时间设置为过去时，立即删除cookie
    }

 }