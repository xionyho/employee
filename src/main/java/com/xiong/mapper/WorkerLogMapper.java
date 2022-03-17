package com.xiong.mapper;

import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName LogMapper.java
 * @Description TODO
 * @createTime 2022年03月17日 11:31:00
 */
@Mapper
public interface WorkerLogMapper {
    Worker selectLog(Integer id, String date);

    Boolean insertLog(Integer id, String date, String startDate);

    Boolean updateLog(Integer id, String date, String startDate);
}
