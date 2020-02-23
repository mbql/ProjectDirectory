package com.zm.shiro.shiro;

import com.zm.shiro.bean.Permissions;
import com.zm.shiro.bean.Role;
import com.zm.shiro.bean.User;
import com.zm.shiro.service.LoginService;
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
 * @author mbql
 * 用户认证和鉴权类
 * @date 2020/2/23 16:39
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //对当前用户名进行鉴权
        User userByName = loginService.getUserByName(username);
        //添加角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Role role :
                userByName.getRoles()) {
            //添加角色
            authorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (Permissions perms :
                    role.getPermissions()) {
                authorizationInfo.addStringPermission(perms.getPermissionsName());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String username = (String) authenticationToken.getPrincipal();
        User user = loginService.getUserByName(username);
        if (user == null) {
            //这里返回后会报异常
            return null;
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
