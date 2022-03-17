package com.xiong.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName DataUtils.java
 * @Description TODO
 * @createTime 2022年03月17日 12:01:00
 */
public class DataUtils {
    /**
     * @title LocalDate
     * @description 生成当前日期
     * @author xiongyuhao
     * @updateTime 2022/3/17 12:07
     * @throws
     */
    public static String LocalDate(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);

    }

    /**
     * @title LocalDateTime
     * @description 生成当前日期时间
     * @author xiongyuhao
     * @updateTime 2022/3/17 12:07
     * @throws
     */
    public static String LocalDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

}
