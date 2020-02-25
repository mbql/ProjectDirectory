package com.zm.springbootrabbitmq.send;

import com.zm.springbootrabbitmq.bean.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mbql
 * @date 2020/2/25 16:47
 */
@Component
public class UserSend {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 实体类的传输（springboot完美的支持对象的发送和接收，不需要格外的配置。实体类必须序列化）
     * @param user
     */
    public void sendUser(User user) {
        System.out.println("user send : " + user.getName()+"/"+user.getPassword());
        rabbitTemplate.convertAndSend("user", user);
    }
}
