package com.xiong.controller;

import com.xiong.pojo.Worker;
import com.xiong.result.Result;
import com.xiong.pojo.User;
import com.xiong.service.impl.WorkerInfoServiceImpl;
import com.xiong.service.impl.WorkerServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/worker")
public class WorkerController {
    @Autowired
    private WorkerServiceImpl workerService;

    @Autowired
    private WorkerInfoServiceImpl workerInfoService;


    //员工登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Worker worker, HttpServletRequest request){
        System.out.println("name:"+worker.getUsername()+",password:"+worker.getPassword());
        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(worker.getUsername(), worker.getPassword());
        // 获取 subject 认证主体
        System.out.println("登录token："+token);
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            request.getSession().setAttribute("worker", worker);
            return "redirect:/main.html";
        //    return Result.success(200,"成功",user);
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("error", "用户名或密码错误！");
            return "redirect:/error.html";
        //    return Result.error("失败");
        }
    }

    //查看所有员工
    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    @ResponseBody
    public Result selectAll() {
        List<Worker> userList = new ArrayList<>();
        try {
            userList = workerService.selectAll();

        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", userList);
    }

    //查看指定员工
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public Result selectByName(@RequestParam("username") String username) {
        Worker worker = new Worker();
        try {
            worker = workerService.selectByName(username);
        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        if (worker != null) {
            return Result.success(200, "返回成功", worker);
        } else {
            return Result.error(500, "查不到改该数据");
        }
    }

    //添加员工
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insertUser(@RequestBody Worker worker) {
        if (worker.getUsername() != "" && worker.getPassword() != "") {
            Worker worker1 = workerService.selectByName(worker.getUsername());
            if (worker1 == null) {
             try {
                 //插入员工信息
                 workerService.insertUser(worker);
                 //获取新插入的员工id
                 Integer id = workerService.selectById(worker.getUsername());
                 //将新员工的id插入到附属表中
                 System.out.println(id);
                 workerInfoService.insertInfo(id);
                 return Result.success(200, "插入成功", worker);
             }catch (Exception e){
                 e.printStackTrace();
             }
            } else {
                return Result.error(500, "该用户已被注册！");
            }
        }
        return Result.error(500, "不允许插入空值！");
    }

    //删除员工
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteUser(@RequestParam("username") String username) {
        //判断输入值是否为空
        if (!"".equals(username) && username != null) {
            workerService.deleteUser(username);
            return Result.success(200, "删除成功");
        }else {
            return Result.error(500,"删除失败,输入值为空！");
        }
    }

    //修改员工密码
    @RequestMapping(value = "/updatepwd", method = RequestMethod.GET)
    @ResponseBody
    public Result updatePassWord(Worker worker) {
        //判断为空
        if (!"".equals(worker.getUsername()) && !"".equals(worker.getPassword()) &&
                worker.getUsername() != null && worker.getPassword() != null) {
            //查询是否存在这条数据
            Worker beforeuser = workerService.selectByName(worker.getUsername());
            if (beforeuser != null) {
                workerService.updatePassWord(worker);
                return Result.success(200,"修改成功",worker);
            } else {
                return Result.error(500,"修改失败,不存在该数据");
            }
        }
        return Result.error(500,"修改失败，输入的参数为空");
    }
}



