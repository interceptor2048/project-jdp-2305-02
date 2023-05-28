package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(Long id)  {
        if(userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void updateUser(Long id, User user)  {
        if(userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void switchBlockade(Long id)  {
        User user = getUser(id);
        user.switchBlockade();
        userRepository.save(user);
    }

    public Integer generateKey(Long id)  {
        User user = getUser(id);
        Random random = new Random();
        int key = random.nextInt(8999) + 1000;
        user.setUserKey(key);
        user.setKeyExpirationTime(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
        return key;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
