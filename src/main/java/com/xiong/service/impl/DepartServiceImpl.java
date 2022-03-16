package com.xiong.service.impl;

import com.xiong.mapper.DepartMapper;
import com.xiong.pojo.Depart;
import com.xiong.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName DepartServiceImpl.java
 * @Description TODO
 * @createTime 2022年03月13日 16:27:00
 */
@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartMapper departMapper;

    @Override
    public List<Depart> selectAll() {
        return departMapper.selectAll();
    }

    @Override
    public List<Depart> selectByName(String departname) {
        return departMapper.selectByName(departname);
    }

    @Override
    public Depart selectName(String departname) {
        return departMapper.selectName(departname);
    }

    @Override
    public Boolean insertDepart(Depart depart) {
        return departMapper.insertDepart(depart);
    }

    @Override
    public Boolean updateDepart(Depart depart) {
        return departMapper.updateDepart(depart);
    }

    @Override
    public Boolean updateDepartLeader(Depart depart) {
        return departMapper.updateDepartLeader(depart);
    }

    @Override
    public List<Depart> selectById(Integer id) {
        return departMapper.selectById(id);
    }

    @Override
    public Boolean updateStatus(Integer id,Integer status) {
        return departMapper.updateStatus(id,status);
    }
}
