package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "email-topic", groupId = "email-group")
    public void listenForEmails(String message) {
        String[] parts = message.split(":");
        String emailAddress = parts[0];
        String emailContent = parts[1];

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailAddress);
        mailMessage.setSubject("Изменение пользователя");
        mailMessage.setText(emailContent);

        mailSender.send(mailMessage);
    }
}
