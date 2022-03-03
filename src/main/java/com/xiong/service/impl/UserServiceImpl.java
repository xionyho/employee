package com.xiong.service.impl;

import com.xiong.mapper.UserMapper;
import com.xiong.pojo.User;
import com.xiong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2022年03月02日 17:04:00
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public Boolean insertUser(User user) {
        return userMapper.insertUser(user.getName(),user.getPassword());
    }

    @Override
    public Boolean deleteUser(String name) {
        return userMapper.deleteUser(name);
    }

    @Override
    public Boolean updatePassWord(User user) {
        return userMapper.updatePassWord(user.getName(),user.getPassword());
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

}
