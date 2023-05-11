package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void switchBlockade(Long id) {
        User user = getUser(id);
        user.switchBlockade();
        userRepository.save(user);
    }

    public Integer generateKey(Long id) {
        Random random = new Random();
        int key = random.nextInt(8999) + 1000;
        User user = getUser(id);
        user.setUserKey(key);
        userRepository.save(user);
        return key;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
