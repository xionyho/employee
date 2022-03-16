package com.xiong.service.impl;

import com.xiong.mapper.PostMapper;
import com.xiong.pojo.Post;
import com.xiong.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName PortServiceImpl.java
 * @Description TODO
 * @createTime 2022年03月16日 15:30:00
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> selectAll() {
        return postMapper.selectAll();
    }

    @Override
    public List<Post> selectByDepName(String departname) {
        return postMapper.selectByDepName(departname);
    }

    @Override
    public List<Post> selectByPostName(String postname) {
        return postMapper.selectByPostName(postname);
    }

    @Override
    public Post selectByName(String postname) {
        return postMapper.selectByName(postname);
    }

    @Override
    public Boolean insertPost(Post post) {
        return postMapper.insertPost(post);
    }
}
