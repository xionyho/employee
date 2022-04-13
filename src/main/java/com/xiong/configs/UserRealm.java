package com.xiong.configs;

import com.xiong.mapper.WorkerMapper;
import com.xiong.pojo.Worker;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiong
 * @version 1.0.0
 * @ClassName UserRealm.java
 * @Description TODO
 * @createTime 2022年03月07日 10:08:00
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    //权限验证，验证某个已认证的用户是否拥有某个权限
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       // 获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("用户名："+username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 给该用户设置角色，角色信息存在 t_role 表中取
   //     System.out.println("角色为："+workerMapper.getRoles(username));
     //   authorizationInfo.setRoles(workerMapper.getRoles(username));
        // 给该用户设置权限，权限信息存在 t_permission 表中取
    //    System.out.println("权限为："+workerMapper.getPermissions(username));
    //    authorizationInfo.setStringPermissions(workerMapper.getPermissions(username));
        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 根据 Token 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 根据用户名从数据库中查询该用户
        Worker worker = workerMapper.selectByUserName(username);
        System.out.println(worker);
        if(worker != null) {
            // 把当前用户存到 Session 中
            SecurityUtils.getSubject().getSession().setAttribute("worker", worker);
            // 传入用户名和密码进行身份认证，并返回认证信息
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(worker.getUsername(), worker.getPassword(), "UserRealm");
            return authcInfo;
        } else {
            return null;
        }
    }
}
