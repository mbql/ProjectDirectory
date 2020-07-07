package com.ht.kafka.controller;

import com.ht.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mbql
 * @date 2020/3/1 21:00
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer producer;

    @GetMapping("/testSendMsg")
    public String sendMsg() {
        producer.send();
        return "success";
    }
}
