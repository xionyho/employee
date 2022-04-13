package com.xiong.service;

import com.xiong.pojo.Post;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName PortService.java
 * @Description TODO
 * @createTime 2022年03月16日 15:30:00
 */
@Service
public interface PostService {
    List<Post> selectAll();

    List<Post> selectByDepName(String departname);

    List<Post> selectByPostName(String postname);

    Post selectByName(String postname);

    Post selectName(String postname,String departname);

    Boolean insertPost(Post post);

    List<Post> selectDepPostName(String departname, String postname);

    Boolean deletePost(Integer id);

    Post selectById(Integer id);

    Boolean updatePost(Post post);

    Boolean updatePostName(Post post);
}
