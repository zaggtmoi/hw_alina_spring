package org.example.service;

import org.example.dao.UserRepository;
import org.example.exception.DaoException;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void createNewUser(User user) throws DaoException {
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
    }

    public void update(User user) throws DaoException {
        userRepository.save(user);
    }

    public void delete(long id) throws DaoException {
        userRepository.deleteById(id);
    }

}
