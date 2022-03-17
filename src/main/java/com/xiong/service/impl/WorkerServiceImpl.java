package com.xiong.service.impl;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.xiong.mapper.WorkerMapper;
import com.xiong.pojo.Worker;
import com.xiong.service.WorkerService;
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
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public Worker selectByName(String name) {
        return workerMapper.selectByName(name);
    }


    @Override
    public Boolean insertUser(Worker worker) {
        return workerMapper.insertUser(worker);
    }

    @Override
    public Boolean deleteUser(String username) {
        return workerMapper.deleteUser(username);
    }


    @Override
    public Boolean updatePassWord(Worker worker) {
        return workerMapper.updatePassWord(worker.getUsername(),worker.getPassword());
    }

    @Override
    public List<Worker> selectAll() {
        return workerMapper.selectAll();
    }

    @Override
    public Integer selectId(String username) {
        return workerMapper.selectId(username);
    }
}
