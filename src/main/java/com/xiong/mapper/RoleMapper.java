package com.xiong.mapper;

import com.xiong.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName RoleMapper.java
 * @Description TODO
 * @createTime 2022年04月11日 14:28:00
 */
@Mapper
@Repository
public interface RoleMapper {
    List<Role> selectAll();
}
