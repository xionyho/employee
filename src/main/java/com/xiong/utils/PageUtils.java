package com.xiong.utils;

import com.github.pagehelper.PageInfo;
import com.xiong.pojo.Page;
import com.xiong.result.PageResult;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName PageUtils.java
 * @Description TODO
 * @createTime 2022年04月08日 22:33:00
 */
public class PageUtils {
    /**
     * 将分页信息封装到统一的接口
     * @param
     * @param
     * @return
     */
    public static PageResult getPageResult(PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
