package com.xiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName Page.java
 * @Description TODO
 * @createTime 2022年04月08日 22:29:00
 */


@Data
@AllArgsConstructor
@NoArgsConstructor

//分页请求
public class Page {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
}
