package com.xiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  //岗位id
  private Integer id;
  //岗位名称
  private String postname;
  //所属部门
  private String departname;

}
