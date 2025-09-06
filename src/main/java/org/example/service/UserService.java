package org.example.service;

import lombok.NonNull;
import org.example.dao.UserRepository;
import org.example.kafka.EmailProducer;
import org.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    final UserRepository userRepository;
    final EmailProducer emailProducer;

    @Autowired
    public UserService(UserRepository userRepository, EmailProducer emailProducer) {
        this.userRepository = userRepository;
        this.emailProducer = emailProducer;
    }

    public User getById(long id) {
        User user = userRepository.findById(id).orElse(null);
        String infoMsg = "Got user by id " + id + ": " + getUserString(user);
        logger.info(infoMsg);
        return user;
    }

    public void createNewUser(@NonNull User user) {
        user.setCreatedAt(Instant.now());
        //todo log
        userRepository.save(user);
        if (user.getEmail() != null) {
            emailProducer.sendEmailNotification(user.getEmail(),
                    "Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан");
        }
    }

    public void update(User user) {
        //todo log
        userRepository.save(user);
    }

    public void delete(long id) {
        //todo log
        User user = getById(id);
        userRepository.deleteById(id);
        if (user.getEmail() != null) {
            emailProducer.sendEmailNotification(user.getEmail(),
                    "Здравствуйте! Ваш аккаунт был удалён");
        }
    }

    private String getUserString(User user) {
        return user == null ? "no user" : user.toString();
    }
}
