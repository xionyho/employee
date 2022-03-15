package com.xiong.service;

import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName WorkerInfoService.java
 * @Description TODO
 * @createTime 2022年03月12日 17:03:00
 */

@Service
public interface WorkerInfoService {
    public List<WorkerInfo> showInfo(Integer id);
    Boolean updateInfo(WorkerInfo workerInfo);

    Boolean insertInfo(Integer id);
}
