package com.xiong.mapper;

import com.xiong.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2022年03月02日 17:03:00
 */
@Mapper
public interface UserMapper {

    public User selectByName(String name);

    public Boolean insertUser(@Param("name") String name,@Param("password") String password);

    public Boolean deleteUser(@Param("name") String name);

    public Boolean updatePassWord(@Param("name") String name,@Param("password") String password);

    public List<User> selectAll();
}
