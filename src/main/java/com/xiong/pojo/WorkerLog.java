package com.xiong.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
    //上班打卡时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    //下班打卡时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    //是否打卡 null没打卡，1打了上班卡，2打了下班卡
    private Integer clock;
}
