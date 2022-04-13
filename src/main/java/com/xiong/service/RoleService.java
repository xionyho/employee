package com.xiong.service;

import com.xiong.pojo.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName RoleService.java
 * @Description TODO
 * @createTime 2022年04月11日 14:29:00
 */
@Service
public interface RoleService {
    List<Role> selectAll();
}
