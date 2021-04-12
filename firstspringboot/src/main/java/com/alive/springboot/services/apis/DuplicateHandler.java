package com.alive.springboot.services.apis;

import com.alive.springboot.interfaces.IDuplicate;
import com.alive.springboot.kafka.KafkaConsumerService;
import com.alive.springboot.kafka.KafkaProducerService;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DuplicateHandler {

    @Autowired
    private IDuplicate iDuplicate;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/findduplicate", method = RequestMethod.POST, produces = "application/json", consumes = "text/plain")
    public ResponseEntity<?> duplicate(@RequestBody String messageBody) {
        log.info("Request received to find duplicate");
        try {
            kafkaProducerService.sendMessage(messageBody);
            JsonObject jsonObject = kafkaConsumerService.consume(messageBody);
            return ResponseEntity.ok(jsonObject.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("BAD request");
        }
    }
}
