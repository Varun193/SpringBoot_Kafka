package com.spring;

import com.spring.entity.WikiMedia;
import com.spring.repo.WikimediaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerWikimedia {

    @Autowired
    private WikimediaRepo wikimediaRepo;

    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaConsumerApplication.class);

    @KafkaListener(topics = "wikimedia_producer", groupId = "myGroup")
    public void consume(String eventMessage){

        LOGGER.info(String.format("Event Message Received -> %s", eventMessage));
        WikiMedia wikiMedia = new WikiMedia();
        wikiMedia.setEventdata(eventMessage);

        wikimediaRepo.save(wikiMedia);
    }

}
