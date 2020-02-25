package com.zm.springbootrabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mbql
 * @date 2020/2/25 16:02
 */
@Component
@RabbitListener(queues = "helloQueue")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver1:"+hello);
    }

}
