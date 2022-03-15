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
}
