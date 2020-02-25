package com.zm.springbootrabbitmq.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mbql
 * @date 2020/2/25 17:09
 */
@Component
public class DirectSend {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String msgString="directSender :hello i am hzb";
        System.out.println(msgString);
        amqpTemplate.convertAndSend("directExchange","direct", msgString);
    }

}
