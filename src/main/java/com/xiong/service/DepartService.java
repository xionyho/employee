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
}
