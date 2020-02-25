package com.zm.springbootrabbitmq.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mbql
 * @date 2020/2/25 17:23
 */
@Component
public class TopicSend {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String msg1 = "I am topic.mesaage msg======";
        System.out.println("sender1 : " + msg1);
        amqpTemplate.convertAndSend("exchange", "topic.message", msg1);
        String msg2 = "I am topic.mesaages msg########";
        System.out.println("sender2 : " + msg2);
        amqpTemplate.convertAndSend("exchange", "topic.messages", msg2);
    }
}
