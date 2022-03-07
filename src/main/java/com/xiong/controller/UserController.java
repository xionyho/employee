package com.xiong.controller;

import com.xiong.result.Result;
import com.xiong.pojo.User;
import com.xiong.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2022年03月02日 17:05:00
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request){
        System.out.println("name:"+user.getName()+",password:"+user.getPassword());
        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        // 获取 subject 认证主体
        System.out.println("登录token："+token);
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            request.getSession().setAttribute("user", user);
            return "redirect:/main.html";
        //    return Result.success(200,"成功",user);
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("error", "用户名或密码错误！");
            return "redirect:/error.html";
        //    return Result.error("失败");
        }
    }

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    public Result selectAll() {
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.selectAll();

        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", userList);
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public Result selectByName(@RequestParam("name") String name) {
        User user = new User();
        try {
            user = userService.selectByName(name);
        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        if (user != null) {
            return Result.success(200, "返回成功", user);
        } else {
            return Result.error(500, "查不到改该数据");
        }


    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public Result insertUser(User user) {
        if (user.getName() != "" && user.getPassword() != "") {
            User user1 = userService.selectByName(user.getName());
            if (user1 == null) {
                userService.insertUser(user);
                return Result.success(200, "插入成功", user);
            } else {
                return Result.error(500, "该用户已被注册！");
            }
        }
        return Result.error(500, "不允许插入空值！");
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteUser(@RequestParam("name") String name) {
        //判断输入值是否为空
        if (!"".equals(name) && name != null) {
            userService.deleteUser(name);
            return Result.success(200, "删除成功");
        }else {
            return Result.error(500,"删除失败,输入值为空！");
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public Result updatePassWord(User user) {
        //判断为空
        if (!"".equals(user.getName()) && !"".equals(user.getPassword()) &&
        user.getName() != null && user.getPassword() != null) {
            //查询是否存在这条数据
            User beforeuser = userService.selectByName(user.getName());
            if (beforeuser != null) {
                userService.updatePassWord(user);
                return Result.success(200,"修改成功",user);
            } else {
                return Result.error(500,"修改失败,不存在该数据");
            }
        }
        return Result.error(500,"修改失败，输入的参数为空");
    }
}



