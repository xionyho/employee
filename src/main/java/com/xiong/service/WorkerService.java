package com.xiong.service;

import com.xiong.pojo.Page;
import com.xiong.pojo.Worker;
import com.xiong.result.PageResult;
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
public interface WorkerService{

    public List<Worker> selectByName(String name);

    public Worker selectByUserName(String username);

    public Boolean insertUser(Worker worker);

    public Boolean deleteUser(Integer id);

    public Boolean updatePassWord(Worker worker);

    public PageResult selectAll(Page page);

    List<Worker> selectAllStatus();

    public Integer selectId(String username);

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param page 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(Page page);

}
