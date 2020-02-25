package com.zm.springbootrabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mbql
 * @date 2020/2/25 17:27
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceivers {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessagesReceiver  : " +msg);
    }
}
