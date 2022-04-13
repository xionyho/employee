package com.xiong.controller;

import com.xiong.pojo.Depart;
import com.xiong.result.Result;
import com.xiong.service.impl.DepartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @title selectDepartAll
     * @description  查询所有没有被禁用的部门
     * @author xiong
     * @updateTime 2022/4/11 11:56
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public Result selectDepartAll() {
        List<Depart> departList = new ArrayList<>();
        try {
            departList = departService.selectAllStatus();
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
     * @title selectDepartById
     * @description 根据id查询
     * @author xiong
     * @updateTime 2022/4/9 15:36
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    public Result selectDepartById(@RequestParam("id") Integer id) {
        Map<String,Object> departMap = new HashMap<>();
        try {
            departMap.put("departList",departService.selectDepartById(id));
        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", departMap);
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
        depart.setStatus(1);
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
    public Result updateDepart(@RequestBody Depart depart,@RequestParam("ischange") Integer ischange){
        Depart depart1 = new Depart();
        System.out.println(depart+":"+ischange);
        depart1 = departService.selectName(depart.getDepartname());
        //领导人被修改了
        if (ischange ==1){
            //名称被修改后是否有重复名称
            if(depart1 == null){
                departService.updateDepart(depart);
                return Result.success(200,"修改成功");
                //判断重复的是否为同一条数据
        }else if(depart.getId() == depart1.getId()){
                departService.updateDepartLeader(depart);
                return Result.success(200,"修改成功");
        }
            return Result.success(400,"已经存在该部门，请重新输入！");
        }else{//领导人没有改
            if(depart1 != null){
                if (depart.getId() == depart1.getId()) {
                    return Result.success(200, "修改成功");
                }else{
                    return Result.success(400,"已经存在该部门，请重新输入！");
                }
            }else{
                departService.updateDepartName(depart);
                return Result.success(200, "修改成功");
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updatestatus", method =RequestMethod.GET)
    public Result updateStatus(@RequestParam("id") Integer id,@RequestParam("status") Integer status){
      departService.updateStatus(id,status);
      return Result.success(200,"操作成功");
    }

    //删除部门
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteUser(@RequestParam("id") Integer id) {
        //判断输入值是否为空
        if (!"".equals(id) && id != null) {
            departService.deletePost(id);
            return Result.success(200, "删除成功");
        }else {
            return Result.error(400,"删除失败");
        }
    }

}

