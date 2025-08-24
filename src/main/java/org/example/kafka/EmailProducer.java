package org.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {
    private static final String EMAIL_TOPIC = "email-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEmailNotification(String emailAddress, String message) {
        String emailPayload = emailAddress + ":" + message;
        kafkaTemplate.send(EMAIL_TOPIC, emailPayload);
    }
}
