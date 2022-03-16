package com.xiong.utils;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName UpdataStatus.java
 * @Description TODO
 * @createTime 2022年03月16日 15:15:00
 */
public class UpdataStatusUtils {

    private static Integer updateStatus(Integer status) {
        if (status == 0){
            status = 1;
        }
            status = 0;
        return status;
    }
}
