package com.smartosc.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 04/08/2020 - 5:11 PM
 * @created_by Hieupv
 * @since 04/08/2020
 */
@Component
public class StartupListener implements ApplicationRunner {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage(String msg) {
        kafkaTemplate.send("test", msg);
    }
    @KafkaListener(topics = "demo", groupId = "group-id")
    public void listen(String message) {
        System.out.println("Received Message in group - group-id: " + message);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 2; i++) {
            sendMessage("Now: " + new Date());
            Thread.sleep(2000);
        }
    }

}
