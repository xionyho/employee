package com.xiong.mapper;

import com.xiong.pojo.Depart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName DepartMapper.java
 * @Description TODO
 * @createTime 2022年03月13日 16:26:00
 */
@Mapper
public interface DepartMapper {
    List<Depart> selectAll();
}
