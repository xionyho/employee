package com.xiong.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

  private Integer id;
  private String permissionname;
  private Integer roleId;


}
