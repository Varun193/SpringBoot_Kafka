package com.spring.KafkaDemo.controller;

import com.spring.KafkaDemo.KafkaDemoApplication;
import com.spring.KafkaDemo.kafka.JsonKafkaProducer;
import com.spring.KafkaDemo.kafka.KafkaProducer;
import com.spring.KafkaDemo.payload.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired JsonKafkaProducer jsonKafkaProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message){

        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message published");
    }

    @PostMapping("/publishJson")
    public ResponseEntity<?> jsonPublish(@RequestBody  User user) {
        jsonKafkaProducer.JsonSendMessage(user);
        return ResponseEntity.ok("Json Message published");
    }
}
