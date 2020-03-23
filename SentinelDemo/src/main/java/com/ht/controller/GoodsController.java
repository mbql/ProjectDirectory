package com.ht.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ht.service.GoodsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mbql
 * @date 2020/3/23 15:41
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsQueryService goodsQueryService;

    @RequestMapping("/queryGoodsInfo")
    @ResponseBody
    public String queryGoodsInfo(@RequestParam("spuId") String spuId) {
        return goodsQueryService.queryGoodsInfo(spuId);
    }

    /**
     * 代码不加任何限流 熔断
     * @return
     */
    @RequestMapping("/test")
    @SentinelResource("test")
    public String test() {
        return "test";
    }
}
