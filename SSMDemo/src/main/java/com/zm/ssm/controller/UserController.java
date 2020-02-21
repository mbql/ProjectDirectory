package com.zm.ssm.controller;

import com.zm.ssm.entity.User;
import com.zm.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-02-21 15:02:26
 */
@Controller
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @RequestMapping("findById/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        User user = userService.queryById(id);
        model.addAttribute("user",user);
        return "queryId";
    }

}
