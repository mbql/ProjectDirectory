package com.zm.springbootrabbitmq.receiver;

import com.zm.springbootrabbitmq.bean.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author mbql
 * @date 2020/2/25 16:54
 */
@Component
@RabbitListener(queues = "user")
public class UserReceiver {

    @RabbitHandler
    public void process(User user){
        System.out.println("user receive  : " + user.getName()+"/"+user.getPassword());
    }
}
