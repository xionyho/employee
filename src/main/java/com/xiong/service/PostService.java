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

    Boolean insertPost(Post post);
}
