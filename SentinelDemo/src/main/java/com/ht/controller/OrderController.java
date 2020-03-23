package com.ht.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.ht.service.OrderQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mbql
 * @date 2020/3/23 14:26
 */
@Controller
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderQueryService orderQueryService;

    private static final String KEY = "getOrderInfo";       //定义要被限流的资源

    /**
     * 配置sentinel限流规则
     */
    @PostConstruct
    public void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource(KEY);
        // QPS控制在2以内
        rule1.setCount(2);
        // QPS限流
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 一、 通过编程式的方式实现限流
     * @param orderId
     * @return
     */
    @RequestMapping("getOrder")
    @ResponseBody
    public String queryOrder1(@RequestParam("orderId") String orderId) {
        Entry entry = null;
        // 资源名
        String resourceName = KEY;
        try {
            // entry可以理解成入口登记
            entry = SphU.entry(resourceName);
            // 被保护的逻辑, 这里为订单查询接口
            return orderQueryService.queryOrderInfo(orderId);
        } catch (BlockException blockException) {
            // 接口被限流的时候, 会进入到这里
            log.warn("---getOrder1接口被限流了---, exception: ", blockException);
            return "接口限流, 返回空";
        } finally {
            // SphU.entry(xxx) 需要与 entry.exit() 成对出现,否则会导致调用链记录异常
            if (entry != null) {
                entry.exit();
            }
        }
    }

    /**
     * 二、 声明式对业务资源限流
     * 1、加入jar包对注解的支持
     * 2、编写Sentinel切面配置类
     * 3、对业务接口实现类对限流逻辑的配置及限流异常处理
     * @param orderId
     * @return
     */
    @RequestMapping("getOrder2")
    @ResponseBody
    public  String getOrder2(@RequestParam(value = "orderId") String orderId){
        return orderQueryService.queryOrderInfo2(orderId);
    }
}
