package com.xiong.service.impl;

import com.xiong.mapper.WorkerLogMapper;
import com.xiong.pojo.Worker;
import com.xiong.service.WorkerLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName LogServiceImpl.java
 * @Description TODO
 * @createTime 2022年03月17日 11:32:00
 */
@Service
public class WorkerLogServiceImpl implements WorkerLogService {

    @Autowired
    private WorkerLogMapper workerLogMapper;

    @Override
    public Worker selectLog(Integer id, String date) {
        return workerLogMapper.selectLog(id,date);
    }

    @Override
    public Boolean insertLog(Integer id, String date, String startDate) {
        return workerLogMapper.insertLog(id,date,startDate);
    }

    @Override
    public Boolean updateLog(Integer id, String date, String startDate) {
        return workerLogMapper.updateLog(id,date,startDate);
    }
}
