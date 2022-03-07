package com.xiong.configs;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiong
 * @version 1.0.0
 * @ClassName ShiroConfig.java
 * @Description TODO
 * @createTime 2022年03月07日 10:07:00
 */
@Configuration
public class ShiroConfig {

    @Bean
    public UserRealm myAuthRealm() {
        UserRealm realm = new UserRealm();
        return realm;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        // 定义 shiroFactoryBean
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        // 设置自定义的 securityManager
        shiroFilter.setSecurityManager(securityManager);
        // 设置默认登录的 URL，身份认证失败会访问该 URL
        shiroFilter.setLoginUrl("/login.html");
        // 设置未授权界面，权限认证失败会访问该 URL
    //    shiroFilter.setUnauthorizedUrl("/login.html");
        // LinkedHashMap 是有序的，进行顺序拦截器配置
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/index.html", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/user/login","anon");
        // “/user/student” 开头的用户需要角色认证，是“administrator”才允许
        filterMap.put("/user/**", "roles[administrator]");
        // “/user/teacher” 开头的用户需要权限认证，是“user:create”才允许

        filterMap.put("/employee/*", "roles[employee]");
      //  filterMap.put("/employee/add", "perms[\"business:add\"]");
        filterMap.put("/employee/delete","perms[business:delete]");
        filterMap.put("/employee/add","perms[business:add]");
        filterMap.put("/**", "authc");
        filterMap.put("/logout","logout");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
  }
}
