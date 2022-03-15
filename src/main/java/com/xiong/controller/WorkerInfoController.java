package com.xiong.controller;

import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerInfo;
import com.xiong.result.Result;
import com.xiong.service.impl.WorkerInfoServiceImpl;
import com.xiong.service.impl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    public Result showInfo(Integer id) {
        List<WorkerInfo> infoList = new ArrayList<>();
        try {
            infoList = workerInfoService.showInfo(id);

        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", infoList);
    }

    //修改人员信息
    @RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
    @ResponseBody
    public Result updateInfo(@RequestBody WorkerInfo workerInfo) {
       try {
           workerInfoService.updateInfo(workerInfo);
           return Result.success(200, "修改成功", workerInfo);
       }catch (Exception e){
           e.printStackTrace();
       }
        return Result.error(500,"修改失败");
    }
}
