package org.example.service;

import org.example.dao.UserRepository;
import org.example.exception.DaoException;
import org.example.kafka.EmailProducer;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    final UserRepository userRepository;
    final EmailProducer emailProducer;

    @Autowired
    public UserService(UserRepository userRepository, EmailProducer emailProducer) {
        this.userRepository = userRepository;
        this.emailProducer = emailProducer;
    }


    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void createNewUser(User user) throws DaoException {
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
        if (user.getEmail() != null) {
            emailProducer.sendEmailNotification(user.getEmail(),
                    "Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан");
        }
    }

    public void update(User user) throws DaoException {
        userRepository.save(user);
    }

    public void delete(long id) throws DaoException {
        User user = getById(id);
        userRepository.deleteById(id);
        if (user.getEmail() != null) {
            emailProducer.sendEmailNotification(user.getEmail(),
                    "Здравствуйте! Ваш аккаунт был удалён");
        }
    }

}
