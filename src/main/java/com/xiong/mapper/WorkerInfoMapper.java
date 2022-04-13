package com.xiong.mapper;

import com.xiong.pojo.Worker;
import com.xiong.pojo.WorkerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName WorkerInfoMapper.java
 * @Description TODO
 * @createTime 2022年03月12日 17:02:00
 */

@Mapper
@Repository
public interface WorkerInfoMapper {
    Worker showInfo(Integer id);

    Boolean updateInfo(@Param("workerInfo") WorkerInfo workerInfo);

    Boolean insertInfo(Integer id,@Param("workerInfo") WorkerInfo workerInfo);

    Boolean updateWorker(@Param("worker") Worker worker);
}
