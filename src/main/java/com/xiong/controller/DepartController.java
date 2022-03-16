package com.xiong.controller;

import com.xiong.pojo.Depart;
import com.xiong.result.Result;
import com.xiong.service.impl.DepartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName DepartController.java
 * @Description TODO
 * @createTime 2022年03月13日 16:26:00
 */
@Controller
@RequestMapping(value = "/depart")
public class DepartController {

    @Autowired
    private DepartServiceImpl departService;

    @ResponseBody
    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<Depart> departList = new ArrayList<>();
        try {
            departList = departService.selectAll();
        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", departList);
    }

    /**
     * @title selectDepart
     * @description  输入框根据名字模糊查询
     * @author xiongyuhao
     * @updateTime 2022/3/16 12:08
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public Result selectDepart(@RequestParam("departname") String departname) {
        List<Depart> departList = new ArrayList<>();
        if ("".equals(departname)) {
            //输入为空，返回所有
            departList = departService.selectAll();
            return Result.success(200, "返回成功", departList);
        } else {
            departList = departService.selectByName(departname);
            return Result.success(200, "返回成功", departList);
        }
    }

    /**
     * @title insertDepart
     * @description 新建部门
     * @author xiongyuhao
     * @updateTime 2022/3/16 12:11
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method =RequestMethod.POST)
    public Result insertDepart(@RequestBody Depart depart){
        Depart depart1 = new Depart();
        //检查部门是否存在
        depart1 = departService.selectName(depart.getDepartname());
        if(depart1 == null){
            departService.insertDepart(depart);
            return Result.success(200,"插入成功");
        }
        return Result.error(500,"插入失败，部门已存在");
    }

    /**
     * @title updateDepart
     * @description 根据id修改部门信息
     * @author xiongyuhao
     * @updateTime 2022/3/16 12:11
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/update", method =RequestMethod.POST)
    public Result updateDepart(@RequestBody Depart depart){
        Depart depart1 = new Depart();
        //查询修改后的部门是否存在，有两种情况，部门名称改了(名称不同的，名称相同的)，部门名称没改
        depart1 = departService.selectName(depart.getDepartname());

        if(depart1 == null){
            departService.updateDepart(depart);
            return Result.success(200,"修改成功");
        }
            departService.updateDepartLeader(depart);
            return Result.success(200,"修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "/updatestatus", method =RequestMethod.POST)
    public Result updateStatus(Integer id,Integer status){
      departService.updateStatus(id,status);
      return Result.success(200,"操作成功");
    }

}

