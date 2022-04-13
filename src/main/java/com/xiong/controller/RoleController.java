package com.xiong.controller;

import com.xiong.pojo.Depart;
import com.xiong.pojo.Role;
import com.xiong.result.Result;
import com.xiong.service.RoleService;
import com.xiong.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName RoleController.java
 * @Description TODO
 * @createTime 2022年04月11日 14:28:00
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @ResponseBody
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public Result selectAll(){
        List<Role> roleList = new ArrayList<>();
        try {
            roleList = roleService.selectAll();
        } catch (Exception e) {
            return Result.error(400, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", roleList);
    }
}
