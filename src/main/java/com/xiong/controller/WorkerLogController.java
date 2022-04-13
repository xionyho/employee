package com.xiong.controller;

import com.xiong.pojo.Depart;
import com.xiong.pojo.Page;
import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerLog;
import com.xiong.result.PageResult;
import com.xiong.result.Result;
import com.xiong.service.impl.WorkerLogServiceImpl;
import com.xiong.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName LogController.java
 * @Description TODO
 * @createTime 2022年03月17日 11:25:00
 */
@Controller
@RequestMapping(value = "/log")
public class WorkerLogController {

    @Autowired
    private WorkerLogServiceImpl workerLogService;

    //查询所有打卡记录
    @ResponseBody
    @RequestMapping(value = "/showAll",method = RequestMethod.GET)
    public Result selectAll(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            System.out.println(pageNum+"-----"+pageSize);
            PageResult page = workerLogService.selectAll(new Page(pageNum, pageSize));
            return Result.success(200, "成功返回", page);
        } catch (Exception e) {
            return Result.error(400, "返回失败" + e.getLocalizedMessage());
        }
    }

    //根据名字查看指定员工打卡记录
    @RequestMapping(value = "/selectByName", method = RequestMethod.GET)
    @ResponseBody
    public Result selectByName(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "name" ,required = false) String name) {
        List<Worker> workerLog = new ArrayList<>();
        if ("".equals(name)){
            //输入为空，返回所有
            PageResult page = workerLogService.selectAll(new Page(pageNum, pageSize));
            return Result.success(200, "返回成功", page);
        }else{
            workerLog = workerLogService.selectByName(name);
            if (workerLog.size() != 0){
                return Result.success(200, "返回成功", workerLog);
            }else {
                return Result.success(400, "找不到该数据");
            }

        }
    }

    //查看详情
    @ResponseBody
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public Result selectLog(@RequestParam(value = "id",required = false) Integer id, HttpServletRequest request) {
        Map<String,Object> LogMap = new HashMap<>();
        if (id != null) {
            try {
                LogMap.put("LogList", workerLogService.selectWorkerLog(id));
            } catch (Exception e) {
                return Result.error(500, "返回失败" + e.getLocalizedMessage());
            }
            return Result.success(200, "成功返回", LogMap);
        }else {
            Worker worker1 = (Worker) request.getSession().getAttribute("worker");
            //当前日期
            String date = DataUtils.LocalDate();
            Worker log = new Worker();
            log = workerLogService.selectLog(worker1.getId(),date);
            try {
                LogMap.put("LogList",log);
            } catch (Exception e) {
                return Result.error(400, "返回失败" + e.getLocalizedMessage());
            }
            return Result.success(200, "成功返回", LogMap);
        }
    }

    /**
     * @title LogList
     * @description 员工打卡时，如果当天没有打卡，插入一条新数据，打过卡则修改最后打卡时间
     * @author xiongyuhao
     * @updateTime 2022/3/17 15:48
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public Result LogList(HttpServletRequest request){
        Worker worker1 = (Worker) request.getSession().getAttribute("worker");
        Integer id = worker1.getId();
        //当前日期
        String date = DataUtils.LocalDate();
        //当前日期时间
        String startDate = DataUtils.LocalDateTime();
        Worker log = new Worker();
        log = workerLogService.selectLog(id,date);
        //判断在这一天，这位员工在这天有没有打过卡
        if(log == null){
           workerLogService.insertLog(id,date,startDate);
          return Result.success(200,"上班打卡成功",workerLogService.selectLog(id,date));
          //判断有没有下班打卡
        }else if (log.getWorkerLog().getEndTime() == null) {
            workerLogService.updateLog(id, date, startDate);
            return Result.success(201, "下班打卡成功",workerLogService.selectLog(id,date));
        }
        return Result.error("你已经下班了，请勿重复打卡");
    }

}
