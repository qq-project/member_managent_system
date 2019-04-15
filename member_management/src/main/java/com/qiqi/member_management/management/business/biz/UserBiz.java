package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.management.business.mapper.UserMapper;
import com.qiqi.member_management.management.business.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 14:31
 * @Version 1.0.0
 */
@Service
public class UserBiz {

    @Autowired
    private UserMapper userMapper;

    public User queryUserByName(String username){
        if (null == username){
            return null;
        }
        return userMapper.queryUserByName(username);
    }

    public void addUser(User user){
        userMapper.insert(user);
    }
}
