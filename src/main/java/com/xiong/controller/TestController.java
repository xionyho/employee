package com.xiong.controller;

import com.xiong.result.Result;
import com.xiong.service.impl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiong
 * @version 1.0.0
 * @ClassName TestController.java
 * @Description TODO
 * @createTime 2022年03月07日 16:14:00
 */
@Controller
@RequestMapping(value = "/employee")
public class TestController {

    @Autowired
    private WorkerServiceImpl userService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Result add(HttpServletRequest request) {
        try {
            Object user = request.getSession().getAttribute("user");
            return Result.success(200, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "权限不够");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public Result insert(HttpServletRequest request) {
        try {
            Object user = request.getSession().getAttribute("user");
            return Result.success(200, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "权限不够");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete(HttpServletRequest request) {
        try {
            Object user = request.getSession().getAttribute("user");
            return Result.success(200, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "权限不够");
        }
    }
}
