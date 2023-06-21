package com.spring;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaWikimediaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaWikimediaProducer.class);
    KafkaTemplate<String, String> kafkaTemplate;

    public KafkaWikimediaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_producer";

        //to read real time stream data from wikimedia we use Event Source
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
//        EventSource eventSource = new EventSource.Builder(URI.create(url)).build();
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);

    }

}

