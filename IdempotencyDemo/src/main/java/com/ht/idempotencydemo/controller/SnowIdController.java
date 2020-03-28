package com.ht.idempotencydemo.controller;

import com.ht.idempotencydemo.domain.CommenResult;
import com.ht.idempotencydemo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mbql
 * @date 2020/3/28 16:11
 */
@RestController
@RequestMapping("id")
public class SnowIdController {

    @Autowired
    private TokenService tokenService;

    /**
     * 获取全局唯一Id
     * @return
     */
    @GetMapping
    public CommenResult getId(){
        return new CommenResult("200","请求成功",tokenService.getToken());
    }
}
