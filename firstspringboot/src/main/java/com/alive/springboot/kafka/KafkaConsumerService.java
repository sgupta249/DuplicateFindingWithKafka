package com.alive.springboot.kafka;

import com.alive.springboot.interfaces.IDuplicate;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private IDuplicate iDuplicate;

    private final Logger logger
            = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${kafka.topic.thetechcheck}",
            groupId = "${zookeeper.groupId}")
    public JsonObject consume(String message) {
        logger.info(String.format("Message recieved -> %s", message));
        JsonObject duplicate = new JsonObject();
        try {
            duplicate = iDuplicate.findDuplicate(message);
            return duplicate;
        }catch (Exception e){
            logger.error("Exception : ", e);
        }
        return duplicate;
    }
}
