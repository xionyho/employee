package com.xiong.mapper;

import com.xiong.pojo.Depart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName DepartMapper.java
 * @Description TODO
 * @createTime 2022年03月13日 16:26:00
 */
@Mapper
@Repository
public interface DepartMapper {
    List<Depart> selectAll();

    List<Depart> selectByName(String departname);

    Depart selectDepartById(@Param("id") Integer id);

    Boolean insertDepart(@Param("depart") Depart depart);

    Boolean updateDepart(@Param("depart") Depart depart);

    Boolean updateDepartLeader(@Param("depart") Depart depart);

    List<Depart> selectById(Integer id);

    Depart selectName(String departname);

    Boolean updateStatus(@Param("id") Integer id,@Param("status") Integer status);

    Boolean deletePost(@Param("id") Integer id);

    List<Depart> selectAllStatus();

    Boolean updateDepartName(@Param("depart") Depart depart);
}
