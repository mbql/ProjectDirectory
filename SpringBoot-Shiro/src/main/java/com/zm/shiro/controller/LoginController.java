package com.zm.shiro.controller;

import com.zm.shiro.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mbql
 * 登录处理类
 * @date 2020/2/23 17:42
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login(User user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(), user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            return "login success！！！";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账户或密码错误.....";
        } catch (AuthorizationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresRoles("admin")
    @RequiresPermissions({"add","query"})
    @RequestMapping("/index")
    public String index() {
        return "index!";
    }
}
