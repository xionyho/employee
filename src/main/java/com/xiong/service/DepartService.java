package com.xiong.service;

import com.xiong.pojo.Depart;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName DepartService.java
 * @Description TODO
 * @createTime 2022年03月13日 16:27:00
 */
@Service
public interface DepartService {
    public List<Depart> selectAll();

    public List<Depart> selectByName(String departname);

    public Depart selectName(String departname);

    public Boolean insertDepart(Depart depart);

    public Boolean updateDepart(Depart depart);

    Boolean updateDepartLeader(Depart depart);

    public List<Depart> selectById(Integer id);

    Boolean updateStatus(Integer id,Integer status);

    Boolean deletePost(Integer id);

    Depart selectDepartById(Integer id);

    List<Depart> selectAllStatus();

    Boolean updateDepartName(Depart depart);
}
