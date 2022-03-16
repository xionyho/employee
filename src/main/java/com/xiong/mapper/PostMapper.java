package com.xiong.mapper;

import com.xiong.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName PostMapper.java
 * @Description TODO
 * @createTime 2022年03月16日 15:29:00
 */
@Mapper
public interface PostMapper {
    List<Post> selectAll();

    List<Post> selectByDepName(@Param("departname") String departname);

    List<Post> selectByPostName(@Param("postname") String postname);

    Post selectByName(@Param("postname") String postname);

    Boolean insertPost(@Param("post") Post post);
}
