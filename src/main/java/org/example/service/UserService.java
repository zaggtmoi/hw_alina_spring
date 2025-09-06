package org.example.service;

import lombok.NonNull;
import org.example.dao.UserRepository;
import org.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    }

    public void update(User user) {
        //todo log
        userRepository.save(user);
    }

    public void delete(long id) {
        //todo log
        userRepository.deleteById(id);
    }

    private String getUserString(User user) {
        return user == null ? "no user" : user.toString();
    }
}
