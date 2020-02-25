package com.zm.springbootrabbitmq.controller;

import com.zm.springbootrabbitmq.bean.User;
import com.zm.springbootrabbitmq.send.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mbql
 * @date 2020/2/25 15:59
 */
@RestController
public class ProducerController {

    @Autowired
    private HelloSend helloSend;
    @Autowired
    private UserSend userSend;
    @Autowired
    private DirectSend directSend;
    @Autowired
    private TopicSend topicSend;
    @Autowired
    private FanoutSend fanoutSend;

    @GetMapping("send")
    public String send() {
        helloSend.send();
        return "生产者发送成功";
    }

    @GetMapping("sendList")
    public String sendList() {
        for (int i = 0; i < 10; i++) {
            helloSend.sendList("helloMsg" + i);
        }
        return "生产者发送成功";
    }

    @GetMapping("user")
    public String sendUser() {
        User user = new User();
        user.setName("程序员");
        user.setPassword("123456");
        userSend.sendUser(user);
        return "发送User对象成功！！！";
    }

    @GetMapping("direct")
    public String direct() {
        directSend.send();
        return "点对点发送成功到队列";
    }

    @GetMapping("topic")
    public String topic() {
        topicSend.send();
        return "主题类型匹配发送到队列";
    }

    @GetMapping("fanout")
    public String fanout() {
        fanoutSend.send();
        return "广播类型数据发送到队列";
    }
}
