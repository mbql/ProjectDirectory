package com.zm.security.controller;

import com.zm.security.core.entity.SysUserEntity;
import com.zm.security.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author mbql
 * @date 2020/2/21 21:02
 */
@Controller
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    /**
     * index 请求方法
     * @return
     */
    @GetMapping("index")
    @ResponseBody
    public String index(){
        return "hello world , security";
    }

    @RequestMapping("doLogin")
    public String showLogin(){
        return "login.html";
    }

    @PostMapping("userLogin")
    public String toLogin(String username,String password){
        SysUserEntity sysUserEntity = sysUserService.selectUserByName(username);
        if (sysUserEntity != null){
            if (password.equals(sysUserEntity.getPassword())){
                return "登录成功！！！";
            }else {
                return "密码有误.....";
            }
        }else {
            return "未找到该用户....";
        }
    }

    @GetMapping("home")
    @ResponseBody
    public String home(){
        return "欢迎来到SpringBootSecurity殿堂.....";
    }

}
