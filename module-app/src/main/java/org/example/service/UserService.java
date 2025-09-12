package org.example.service;

import lombok.NonNull;
import org.example.dao.UserRepository;
import org.example.kafka.EmailProducer;
import org.example.model.User;
import org.example.model.UserDTO;
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

    public UserDTO getById(long id) {
        User user = getUserById(id);
        String infoMsg = "Got user by id " + id + ": " + getUserString(user);
        logger.info(infoMsg);
        return new UserDTO(user);
    }

    private User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO createNewUser(@NonNull UserDTO userDto) {
        User user = userDto.getUser();
        user.setCreatedAt(Instant.now());
        //todo log
        User saved = userRepository.save(user);
        if (user.getEmail() != null) {
            emailProducer.sendEmailNotification(user.getEmail(),
                    "Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан");
        }
        return new UserDTO(saved);
    }

    public UserDTO update(UserDTO user) {
        //todo log
        User saved = userRepository.save(user.getUser());
        return new UserDTO(saved);
    }

    public void delete(long id) {
        //todo log
        User user = getUserById(id);
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
