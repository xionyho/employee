package com.xiong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiong.mapper.WorkerMapper;
import com.xiong.pojo.Page;
import com.xiong.pojo.Worker;
import com.xiong.result.PageResult;
import com.xiong.service.WorkerService;
import com.xiong.utils.PageUtils;
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
    public List<Worker> selectByName(String name) {
        return workerMapper.selectByName(name);
    }

    @Override
    public Worker selectByUserName(String username) {
        return workerMapper.selectByUserName(username);
    }


    @Override
    public Boolean insertUser(Worker worker) {
        return workerMapper.insertUser(worker);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return workerMapper.deleteUser(id);
    }


    @Override
    public Boolean updatePassWord(Worker worker) {
        return workerMapper.updatePassWord(worker.getUsername(),worker.getPassword());
    }

    @Override
    public PageResult selectAll(Page page) {
        List<Worker> workerList = workerMapper.selectAll();
        return PageUtils.getPageResult(getPageInfo(page));
    }

    @Override
    public List<Worker> selectAllStatus() {
        return workerMapper.selectAllStatus();
    }

    @Override
    public Integer selectId(String username) {
        return workerMapper.selectId(username);
    }

    @Override
    public PageResult findPage(Page page) {
        return null;
    }

    /**
     * 调用分页插件完成分页
     * @param
     * @return
     */
    private PageInfo<Worker> getPageInfo(Page page) {
        int pageNum = page.getPageNum();
        int pageSize = page.getPageSize();
        System.out.println(pageNum+":::"+pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<Worker> sysMenus = workerMapper.selectAll();
       // System.out.println(sysMenus);
      //  System.out.println(workerList);
        return new PageInfo<Worker>(sysMenus);
    }

}
