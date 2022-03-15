package com.xiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WorkerInfo {

  //员工信息id
  private Integer id;
  //手机
  private Long phone;
  //邮箱
  private String email;
  //入职时间
  private java.sql.Date entryTime;
  //年龄
  private Integer age;
  //性别
  private String sex;
  //绑定的员工
  private Integer workId;

}
