package com.zm.springbootrabbitmq.receiver.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mbql
 * @date 2020/2/25 17:37
 */
@Component
@RabbitListener(queues = "fanout.C")
public class FanoutC {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("FanoutReceiverC  : " + msg);
    }
}
