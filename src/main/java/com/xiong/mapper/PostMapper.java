package com.xiong.mapper;

import com.xiong.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName PostMapper.java
 * @Description TODO
 * @createTime 2022年03月16日 15:29:00
 */
@Mapper
@Repository
public interface PostMapper {
    List<Post> selectAll();

    Post selectById(@Param("id") Integer id);

    List<Post> selectByDepName(@Param("departname") String departname);

    List<Post> selectByPostName(@Param("postname") String postname);

    Post selectByName(@Param("postname") String postname);

    Post selectName(@Param("postname") String postname,@Param("departname") String departname);

    Boolean insertPost(@Param("post") Post post);

    List<Post> selectDepPostName(@Param("departname")String departname, @Param("postname")String postname);

    Boolean deletePost(@Param("id") Integer id);

    Boolean updatePost(@Param("post") Post post);

    Boolean updatePostName(@Param("post") Post post);

}
