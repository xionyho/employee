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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private DepartServiceImpl departService;

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
     * @title selectById
     * @description  根据id查询
     * @author xiong
     * @param: id
     * @updateTime 2022/4/9 16:24
     * @return: com.xiong.result.Result
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    public Result selectById(@RequestParam("id") Integer id) {
        Map<String,Object> postMap = new HashMap<>();
        try {
            Post post = postService.selectById(id);
            postMap.put("postData",post);
        } catch (Exception e) {
            return Result.error(500, "返回失败" + e.getLocalizedMessage());
        }
        return Result.success(200, "成功返回", postMap);
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

    @ResponseBody
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public Result selectByName(@RequestParam(value = "departname",required = false) String departname,@RequestParam(value="postname",required = false) String postname) {
        List<Post> postList = new ArrayList<>();
        if (departname == null && postname == null) {
            //输入为空，返回所有
            postList = postService.selectAll();
            return Result.success(200, "返回成功", postList);
            //只输入部门名称
        } else if(departname != null && postname == null){
            postList = postService.selectByDepName(departname);
            return Result.success(200, "返回成功", postList);
            //只输入岗位名称
        }else if(departname == null && postname != null){
            postList = postService.selectByPostName(postname);
            return Result.success(200, "返回成功", postList);
        }else{
            //都输入
            postList = postService.selectDepPostName(departname,postname);
            return Result.success(200, "返回成功",postList);
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
        post1 = postService.selectName(post.getPostname(),post.getDepartname());
        if(post1 == null){
            postService.insertPost(post);
            return Result.success(200,"插入成功");
        }
        return Result.error(400,"岗位名称已存在");
    }

    //修改岗位
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePost(@RequestBody Post post,@RequestParam("ischange") Integer ischange) {
        Post post1 = new Post();
        //部门改变
        if(ischange == 1){
            post1 = postService.selectName(post.getPostname(),post.getDepartname());
            if(post1 == null){
                System.out.println(post);
                postService.updatePost(post);
                return Result.success(200, "修改成功");
            }else if(post.getId() == post1.getId()){
                return Result.success(201,"修改成功");
            }else {
                return Result.error(400,"该岗位名称已存在，请重新输入");
            }
        }else{//部门没有改变
            try {
                Post post2 = postService.selectName(post.getPostname(), String.valueOf(departService.selectName(post.getDepartname()).getId()));
                if (post2 != null) {
                    if (post.getId() == post2.getId()) {
                        return Result.success(201, "修改成功");
                    }
                    return Result.error(400,"该岗位名称已存在，请重新输入");
                }
                postService.updatePostName(post);
                return Result.success(200, "修改成功");
            }catch (Exception e){
                    return Result.error(400,"该岗位名称已存在，请重新输入");
            }
        }

    }

    //删除岗位
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result deletePost(@RequestParam("id") Integer id) {
        //判断输入值是否为空
        if (!"".equals(id) && id != null) {
            postService.deletePost(id);
            return Result.success(200, "删除成功");
        }else {
            return Result.error(400,"删除失败");
        }
    }

}
