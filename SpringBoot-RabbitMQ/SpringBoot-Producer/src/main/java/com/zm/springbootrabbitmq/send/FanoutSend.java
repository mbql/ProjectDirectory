package com.zm.springbootrabbitmq.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mbql
 * @date 2020/2/25 17:35
 */
@Component
public class FanoutSend {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String msgString="fanoutSender :hello i am hzb";
        System.out.println(msgString);
        // 参数2被忽略
        amqpTemplate.convertAndSend("fanoutExchange","", msgString);
    }
}
