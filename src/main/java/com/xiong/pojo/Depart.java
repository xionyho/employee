package com.xiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Depart {

  //部门id
  private Integer id;
  //部门名称
  private String departname;
  //部门领导人
  private String leader;
  //启用状态
  private Integer status;
}
