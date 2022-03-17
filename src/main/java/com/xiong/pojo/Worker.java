package com.xiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
  //员工的id
  private Integer id;
  //员工姓名
  private String name;
  //员工登录号
  private String username;
  //员工密码
  private String password;
  //部门
  private String department;
  //岗位
  private String post;
  //备注
  private String remarks;
  //员工的角色
  private Integer roleId;
  //状态
  private Integer status;
  //员工信息
  private WorkerInfo workerInfo;
  //员工打卡信息
  private WorkerLog workerLog;
}
