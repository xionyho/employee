package com.xiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2022年03月02日 17:01:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String password;

}
