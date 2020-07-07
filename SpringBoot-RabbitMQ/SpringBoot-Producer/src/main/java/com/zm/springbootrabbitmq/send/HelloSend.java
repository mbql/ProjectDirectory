package com.zm.springbootrabbitmq.send;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author mbql
 * @date 2020/2/25 15:54
 */
@Component
public class HelloSend {

    /**
     *  AmqpTemplate可以说是RabbitTemplate父类，RabbitTemplate实现类RabbitOperations接口，RabbitOperations继承了AmqpTemplate接口
      */
    // @Autowired
    // private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 用于单生产者-》单消费者测试
     */
    public void send(){
        String hello ="hello"+new Date();
        System.out.println("Send:"+hello);
        rabbitTemplate.convertAndSend("helloQueue",hello);
    }

    /**
     * 用于单/多生产者-》多消费者测试
     */
    public void sendList(String msg) {
        String sendMsg = msg + new Date();
        System.out.println("Sender1 : " + sendMsg);
        rabbitTemplate.convertAndSend("helloQueue", sendMsg);
    }

}
