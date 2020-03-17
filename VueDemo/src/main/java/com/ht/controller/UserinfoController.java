package com.ht.controller;

import com.ht.entity.Userinfo;
import com.ht.service.UserinfoService;
import com.ht.util.ResultToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户表(Userinfo)表控制层
 *
 * @author makejava
 * @since 2020-03-11 16:39:50
 */
@RestController
@RequestMapping("api")
@CrossOrigin
public class UserinfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserinfoService userinfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("findUserById")
    public Userinfo selectOne(Integer id) {
        return this.userinfoService.queryById(id);
    }

    @PostMapping(value = "login")
    public Object login(@RequestBody Userinfo user){
        ResultToken login = this.userinfoService.login(user);
        int code = 200;
        if (login.getResultMessage().getCode() == code){
            return login.wrapData(login.getToken(),login.getResultMessage());
        }
        return login.wrapData(login.getToken(),login.getResultMessage());
    }
}
