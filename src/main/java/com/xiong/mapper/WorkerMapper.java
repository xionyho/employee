package com.xiong.mapper;

import com.xiong.pojo.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description TODO
 * @createTime 2022年03月02日 17:03:00
 */
@Mapper
@Repository
public interface WorkerMapper{

    public List<Worker> selectByName(String name);

    public Worker selectByUserName(String username);

    public Boolean insertUser(@Param("worker") Worker worker);

    public Boolean deleteUser(@Param("id") Integer id);

    public Boolean updatePassWord(@Param("username") String username,@Param("password") String password);

    public List<Worker> selectAll();

    public Set<String> getRoles(String username);

    public Set<String> getPermissions(String username);

    public Integer selectId(@Param("username") String username);

    /**
     * 分页查询用户
     * @return
     */
    List<Worker> selectPage();

    List<Worker> selectAllStatus();
}
