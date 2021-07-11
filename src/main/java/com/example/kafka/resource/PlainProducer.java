package com.example.kafka.resource;

import com.example.kafka.model.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;


@RestController
@RequestMapping("plain")
public class PlainProducer {


    @RequestMapping("/saveuser/{name}")
    public String plainProducer(@PathVariable String name){
        String bootstrapServers = "localhost:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer .class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());


        KafkaProducer<String, User> producer = new KafkaProducer<String, User>(properties);

        ProducerRecord<String, User> record = new ProducerRecord<String, User>("java_topic", new User(name, "TECH", 120));
        producer.send(record);
        producer.flush();
        producer.close();
        return "Sent successful";
    }

}
