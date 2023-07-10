package com.spring.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class OrderConfig {


    @Value("{spring.kafka.topic.name}")
    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name("orders_topics").build(); // create partition here if you want
                                                           // otherwise default partition is considered
    }

}
