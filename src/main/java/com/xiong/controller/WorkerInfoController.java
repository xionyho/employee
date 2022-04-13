package com.xiong.controller;

import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerInfo;
import com.xiong.result.Result;
import com.xiong.service.impl.WorkerInfoServiceImpl;
import com.xiong.service.impl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName WorkerInfoController.java
 * @Description TODO
 * @createTime 2022年03月12日 17:02:00
 */

@Controller
@RequestMapping(value = "/workerinfo")
public class WorkerInfoController {

    @Autowired
    private WorkerInfoServiceImpl workerInfoService;

    @Autowired
    private WorkerServiceImpl workerService;

    //查看人员全部信息
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public Result showInfo(@RequestParam(value = "id",required = false) Integer id,HttpServletRequest request) {
        Map<String,Object> infoMap = new HashMap<>();
        if (id != null){
            try {
                infoMap.put("worker",workerInfoService.showInfo(id));

            } catch (Exception e) {
                return Result.error(400, "返回失败" + e.getLocalizedMessage());
            }
            return Result.success(200, "成功返回", infoMap);
        }else{
            Worker worker1 = (Worker) request.getSession().getAttribute("worker");
            try {
                infoMap.put("worker",workerInfoService.showInfo(worker1.getId()));
            } catch (Exception e) {
                return Result.error(400, "返回失败" + e.getLocalizedMessage());
            }
            return Result.success(200, "成功返回", infoMap);
        }

    }

    //修改登录人员信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Worker worker) {
        try {
            workerInfoService.updateInfo(worker.getWorkerInfo());
        return Result.success(200, "修改成功", worker);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error(400,"修改失败");
    }

    //修改人员信息
    @RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
    @ResponseBody
    public Result updateInfo(@RequestBody Worker worker) {
       try {
           System.out.println("worker:"+worker);
           System.out.println("WorkerInfo:"+worker.getWorkerInfo());
           workerInfoService.updateWorker(worker);
           workerInfoService.updateInfo(worker.getWorkerInfo());
           return Result.success(200, "修改成功", worker);
       }catch (Exception e){
           e.printStackTrace();
       }
        return Result.error(400,"修改失败");
    }
}
