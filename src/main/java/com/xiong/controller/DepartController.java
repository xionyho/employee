package com.xiong.controller;

import com.xiong.pojo.Depart;
import com.xiong.result.Result;
import com.xiong.service.impl.DepartServiceImpl;
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
    @RequestMapping(value = "/showAll",method = RequestMethod.GET)
    public Result selectAll(){
        List<Depart> departList = new ArrayList<>();
        try {
            departList = departService.selectAll();
        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", departList);
    }
    }

