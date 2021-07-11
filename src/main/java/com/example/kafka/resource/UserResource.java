package com.example.kafka.resource;


import com.example.kafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private final String TOPIC = "java_topic";

    @GetMapping("/saveuser/{name}")
    public String pushData(@PathVariable("name") String name) {
        User user = new User(name, "Ilabs", 1000000);
        kafkaTemplate.send(TOPIC, user);
        return "Sent Successfully";
    }
}
