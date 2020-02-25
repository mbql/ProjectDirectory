package com.zm.springbootrabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mbql
 * @date 2020/2/25 17:14
 */
@Component
@RabbitListener(queues = "direct")
public class DirectReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("directReceiver  : " + msg);
    }
}
