package com.xiong.controller;

import com.xiong.pojo.Depart;
import com.xiong.pojo.Post;
import com.xiong.result.Result;
import com.xiong.service.impl.DepartServiceImpl;
import com.xiong.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName postController.java
 * @Description TODO
 * @createTime 2022年03月16日 15:29:00
 */
@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @ResponseBody
    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<Post> postList = new ArrayList<>();
        try {
            postList = postService.selectAll();
        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", postList);
    }

    /**
     * @title selectDepart
     * @description  根据部门名称进行模糊搜索
     * @author xiongyuhao
     * @updateTime 2022/3/16 16:39
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/selectdepname", method = RequestMethod.GET)
    public Result selectDepName(@RequestParam("departname") String departname) {
        List<Post> postList = new ArrayList<>();
        if ("".equals(departname)) {
            //输入为空，返回所有
            postList = postService.selectAll();
            return Result.success(200, "返回成功", postList);
        } else {
            postList = postService.selectByDepName(departname);
            return Result.success(200, "返回成功", postList);
        }
    }

    /**
     * @title selectDepName
     * @description  根据岗位名称进行模糊查询
     * @author xiongyuhao
     * @updateTime 2022/3/16 16:54
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/selectpostname", method = RequestMethod.GET)
    public Result selectPostName(@RequestParam("postname") String postname) {
        List<Post> postList = new ArrayList<>();
        if ("".equals(postname)) {
            //输入为空，返回所有
            postList = postService.selectAll();
            return Result.success(200, "返回成功", postList);
        } else {
            postList = postService.selectByPostName(postname);
            return Result.success(200, "返回成功", postList);
        }
    }

    /**
     * @title insertPost
     * @description 新增岗位
     * @author xiongyuhao
     * @updateTime 2022/3/17 9:13
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insertPost(@RequestBody Post post) {
        Post post1 = new Post();
        post1 = postService.selectByName(post.getPostname());
        if(post1 == null){
            postService.insertPost(post);
            return Result.success(200,"插入成功");
        }
        return Result.error(500,"岗位名称已存在");
    }
}
