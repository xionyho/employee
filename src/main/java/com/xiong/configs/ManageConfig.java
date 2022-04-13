package com.xiong.configs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiongyuhao
 * @version 1.0.0
 * @ClassName ManageConfig.java
 * @Description TODO
 * @createTime 2022年03月31日 17:08:00
 */
@Configuration
@MapperScan("com.xiong.mapper")
public class ManageConfig {
    /*
     * 分页插件
     * */
    /*public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }*/

}
