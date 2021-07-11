package com.example.kafka;


import com.example.kafka.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "java_topic_string", groupId = "t1", containerFactory = "kafkaListenerContainerFactory")
    public void consumer(String message) {
        System.out.println("Consumed STRING message: " + message);
    }


    @KafkaListener(topics = "java_topic_json", containerFactory = "userKafkaListenerFactory", groupId = "t2")
    public void consumerJSON(User user) {
        System.out.println("Consumed JSON message: " + user);
    }
}
