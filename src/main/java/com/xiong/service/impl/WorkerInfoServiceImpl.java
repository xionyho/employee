package com.xiong.service.impl;

import com.xiong.mapper.WorkerInfoMapper;
import com.xiong.mapper.WorkerMapper;
import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerInfo;
import com.xiong.service.WorkerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName WorkerInfoImpl.java
 * @Description TODO
 * @createTime 2022年03月12日 17:03:00
 */

@Service
public class WorkerInfoServiceImpl implements WorkerInfoService {

    @Autowired
    private WorkerInfoMapper workerInfoMapper;

    @Override
    public Worker showInfo(Integer id) {
        return workerInfoMapper.showInfo(id);
    }

    @Override
    public Boolean updateInfo(WorkerInfo workerInfo) {
        return workerInfoMapper.updateInfo(workerInfo);
    }

    @Override
    public Boolean insertInfo(Integer id,WorkerInfo workerInfo) {
        return workerInfoMapper.insertInfo(id, workerInfo);
    }

    @Override
    public Boolean updateWorker(Worker worker) {
        return workerInfoMapper.updateWorker(worker);
    }
}
