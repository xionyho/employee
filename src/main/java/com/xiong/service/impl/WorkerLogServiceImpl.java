package com.xiong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiong.mapper.WorkerLogMapper;
import com.xiong.pojo.Page;
import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerLog;
import com.xiong.result.PageResult;
import com.xiong.service.WorkerLogService;
import com.xiong.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageResult selectAll(Page page) {
        return PageUtils.getPageResult(getPageInfo(page));
    }

    @Override
    public Worker selectWorkerLog(Integer id) {
        return workerLogMapper.selectWorkerLog(id);
    }

    /**
     * 调用分页插件完成分页
     * @param
     * @return
     */
    private PageInfo<WorkerLog> getPageInfo(Page page) {
        int pageNum = page.getPageNum();
        int pageSize = page.getPageSize();
        System.out.println(pageNum+":::"+pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<WorkerLog> sysMenus = workerLogMapper.selectAll();
        // System.out.println(sysMenus);
        //  System.out.println(workerList);
        return new PageInfo<WorkerLog>(sysMenus);
    }

    @Override
    public List<Worker> selectByName(String name) {
        return workerLogMapper.selectByName(name);
    }
}


