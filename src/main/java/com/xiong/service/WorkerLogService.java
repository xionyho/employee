package com.xiong.service;

import com.xiong.pojo.Page;
import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerLog;
import com.xiong.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName LogService.java
 * @Description TODO
 * @createTime 2022年03月17日 11:32:00
 */
@Service
public interface WorkerLogService {

    Worker selectLog(Integer id, String date);

    Boolean insertLog(Integer id, String date, String startDate);

    Boolean updateLog(Integer id, String date, String startDate);

    PageResult selectAll(Page page);

    Worker selectWorkerLog(Integer id);

    List<Worker> selectByName(String name);
}
