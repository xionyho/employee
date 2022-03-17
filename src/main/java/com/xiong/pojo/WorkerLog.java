package com.xiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName Log.java
 * @Description TODO
 * @createTime 2022年03月17日 10:57:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerLog {

    //日志主键
    private Integer id;
    //员工id
    private Integer workId;
    //打卡日期
    private Date time;
    //上班打卡时间
    private Date startTime;
    //下班打卡时间
    private Date endTime;
}
