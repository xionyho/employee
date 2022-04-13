package com.xiong.controller;

import com.xiong.pojo.Depart;
import com.xiong.pojo.Page;
import com.xiong.pojo.Worker;
import com.xiong.result.PageResult;
import com.xiong.result.Result;
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
    @ResponseBody
    public Result login(@RequestBody Worker worker,HttpServletRequest request){
        System.out.println("username:"+worker.getUsername()+",password:"+worker.getPassword());
        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(worker.getUsername(), worker.getPassword());
        // 获取 subject 认证主体
        System.out.println("登录token："+token);
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);

           // return "redirect:/main.html";

            return Result.success(200,"登录成功",request.getSession().getAttribute("worker"));
        }catch(Exception e){
            e.printStackTrace();
        //    request.setAttribute("error", "用户名或密码错误！");
          //  return "redirect:/error.html";
            return Result.error(400,"用户名或密码错误！");
        }
    }

    //查看所有员工
    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    @ResponseBody
    public Result selectAll(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            PageResult page = workerService.selectAll(new Page(pageNum, pageSize));
            return Result.success(200, "成功返回", page);
        } catch (Exception e) {
            return Result.error(400, "返回失败" + e.getLocalizedMessage());
        }
    }

    //查看所有在职员工
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    public Result selectAllStatus() {
        List<Worker> workerList = new ArrayList<>();
        try {
            workerList = workerService.selectAllStatus();
        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", workerList);
    }

    //测试分页
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public Result page(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageResult page = workerService.findPage(new Page(pageNum, pageSize));
        return Result.success(200,"成功",page);
    }

    //查看指定员工
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public Result selectByName(@RequestParam("name") String name) {
        List<Worker> worker = new ArrayList<>();
        try {
            worker = workerService.selectByName(name);
        } catch (Exception e) {
            return Result.error(400, "返回失败" + e.getLocalizedMessage());
        }
        if (worker != null) {
            return Result.success(200, "返回成功", worker);
        } else {
            return Result.error(400, "查不到改该数据");
        }
    }

    //添加员工
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insertUser(@RequestBody Worker worker) {
        if (worker.getUsername() != "" && worker.getPassword() != "") {
            worker.setPassword("123456");
            //worker.setStatus(1);
            Worker worker1 = workerService.selectByUserName(worker.getUsername());
            if (worker1 == null) {
             try {
                 //插入员工信息
                 workerService.insertUser(worker);
                 //获取新插入的员工id
                 Integer id = workerService.selectId(worker.getUsername());
                 //将新员工的id插入到附属表中
                 System.out.println(id);
                 workerInfoService.insertInfo(id,worker.getWorkerInfo());
                 return Result.success(200, "插入成功", worker);
             }catch (Exception e){
                 e.printStackTrace();
             }
            } else {
                return Result.error(400, "该用户已被注册！");
            }
        }
        return Result.error(400, "不允许插入空值！");
    }

    //删除员工
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteUser(@RequestParam("id") Integer id) {
        //判断输入值是否为空
        if (!"".equals(id) && id != null) {
            workerService.deleteUser(id);
            return Result.success(200, "删除成功");
        }else {
            return Result.error(400,"删除失败");
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
            Worker beforeuser = workerService.selectByUserName(worker.getUsername());
            if (beforeuser != null) {
                workerService.updatePassWord(worker);
                return Result.success(200,"修改成功",worker);
            } else {
                return Result.error(400,"修改失败,不存在该数据");
            }
        }
        return Result.error(400,"修改失败，输入的参数为空");
    }
}



