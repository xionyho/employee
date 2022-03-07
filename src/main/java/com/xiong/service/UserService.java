package com.xiong.service;

import com.xiong.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2022年03月02日 17:04:00
 */
@Service
public interface UserService {

    public User selectByName(String name);

    public Boolean insertUser(User user);

    public Boolean deleteUser(String name);

    public Boolean updatePassWord(User user);

    public List<User> selectAll();


}
