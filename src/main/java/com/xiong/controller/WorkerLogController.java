package com.xiong.controller;

import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerLog;
import com.xiong.result.Result;
import com.xiong.service.impl.WorkerLogServiceImpl;
import com.xiong.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName LogController.java
 * @Description TODO
 * @createTime 2022年03月17日 11:25:00
 */
@Controller
public class WorkerLogController {

    @Autowired
    private WorkerLogServiceImpl workerLogService;

    /**
     * @title LogList
     * @description 员工打卡时，如果当天没有打卡，插入一条新数据，打过卡则修改最后打卡时间
     * @author xiongyuhao
     * @updateTime 2022/3/17 15:48
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/log",method = RequestMethod.GET)
    public Result LogList(Integer id){
        //当前日期
        String date = DataUtils.LocalDate();
        //当前日期时间
        String startDate = DataUtils.LocalDateTime();
        Worker log = new Worker();
        log = workerLogService.selectLog(id,date);
        //判断在这一天，这位员工在这天有没有打过卡
        if(log == null){
           workerLogService.insertLog(id,date,startDate);
          return Result.success(200,"上班打卡成功");
          //判断有没有下班打卡
        }else if (log.getWorkerLog().getEndTime() == null) {
            workerLogService.updateLog(id, date, startDate);
            return Result.success(200, "下班打卡成功");
        }
        return Result.error("你已经下班了，请勿重复打卡");
    }

}
