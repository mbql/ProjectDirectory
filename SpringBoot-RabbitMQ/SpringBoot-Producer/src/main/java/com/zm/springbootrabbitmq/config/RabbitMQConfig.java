package com.zm.springbootrabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author mbql
 * @date 2020/2/25 15:37
 */
@Configuration
public class RabbitMQConfig {

    private static Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Resource
    private CachingConnectionFactory connectionFactory;

    final static String queueName = "helloQueue";

    @Bean
    public Queue helloQueue() {
        return new Queue(queueName);
    }

    @Bean
    public Queue userQueue() {
        return new Queue("user");
    }

    @Bean
    public Queue dirQueue() {
        return new Queue("direct");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * 将队列dirQueue与directExchange交换机绑定，routing_key为direct
     *
     * @param dirQueue
     * @param directExchange
     * @return
     */
    @Bean
    Binding bindingExchangeDirect(@Qualifier("dirQueue") Queue dirQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(dirQueue).to(directExchange).with("direct");
    }

    /**
     * Bean默认的name是方法名
     *
     * @return Queue
     */
    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }

    @Bean
    TopicExchange exchange() {
        // 参数1为交换机的名称
        return new TopicExchange("exchange");
    }

    /**
     * 将队列topic.message与exchange绑定，routing_key为topic.message,就是完全匹配
     *
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列topic.messages与exchange绑定，routing_key为topic.#,模糊匹配
     *
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

    //===============以下是验证Fanout Exchange的队列==========

    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        // 参数1为交换机的名称
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);            //默认为false  为true时，开启队列中不可达消息也能够接受到 防止exchange到queue中消息丢失情况
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    logger.info("消息发送成功：correlationData({}),ack({}),cause({})", correlationData, ack, cause);
                } else {
                    logger.info("消息发送失败：correlationData({}),ack({}),cause({})", correlationData, ack, cause);
                }
            }
        });
        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routeKey) {
                logger.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routeKey, replyCode, replyText, message);
            }
        });
        return template;
    }
}
