package com.xiong.service;

import com.xiong.pojo.Worker;
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
public interface WorkerService {

    public Worker selectByName(String username);

    public Boolean insertUser(Worker worker);

    public Boolean deleteUser(String username);

    public Boolean updatePassWord(Worker worker);

    public List<Worker> selectAll();

    public Integer selectById(String username);


}
