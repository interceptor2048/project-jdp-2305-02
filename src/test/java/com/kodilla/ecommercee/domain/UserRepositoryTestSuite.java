package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;


import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTestSuite {
    @Autowired
    UserRepository userRepository;

    @Test
    void testRetrieveAllUsers() {
        //Given
        List<User> initialUserList = userRepository.findAll();

        ArrayList<User> users = new ArrayList<>();
        ArrayList<Long> ids = new ArrayList<>();

        users.add(new User(null, "bsandiford7", 1, 88675));
        users.add(new User(null, "mgopsell8", 1, 63858));
        users.add(new User(null, "mpozzo9", 0, 44519));

        userRepository.saveAll(users);
        for(int i=0; i<3; i++) {
            ids.add(i, users.get(i).getId());
        }

        //When
        List<User> retrievedListOfUsers = userRepository.findAll();

        //Then
        int expectedSize = initialUserList.size() + users.size();
        Assertions.assertEquals(expectedSize, retrievedListOfUsers.size());

        //CleanUp
        for(Long l : ids) {
            userRepository.deleteById(l);
        }
    }

    @Test
    void testRetrieveSingleUser_Existing() {
        //Given
        User user = new User(null, "flettuce5", 1, 35602);

        //When
        userRepository.save(user);
        Long id = user.getId();
        Optional<User> retrievedTestUser = userRepository.findById(id);

        //Then
        Assertions.assertTrue(retrievedTestUser.isPresent());
        Assertions.assertEquals(user.getUsername(), retrievedTestUser.get().getUsername());
        Assertions.assertEquals(user.getStatus(), retrievedTestUser.get().getStatus());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    void testRetrieveSingleUser_NotExisting() {
        //Given

        //When
        Optional<User> retrievedTestUser = userRepository.findById(-1L);

        //Then
        Assertions.assertFalse(retrievedTestUser.isPresent());
    }

    @Test
    void testDeleteUser() {
        //Given
        User user = new User(null, "whambidge6", 1, 90867);
        userRepository.save(user);
        Long id = user.getId();

        //When
        userRepository.deleteById(id);
        Optional<User> retrievedTestUser = userRepository.findById(id);

        //Then
        Assertions.assertFalse(retrievedTestUser.isPresent());
    }

    @Test
    void testCreateUser() {
        //Given
        User user = new User(null, "aboch4", 0, 83614);
        //When
        userRepository.save(user);
        Long id = user.getId();
        Optional<User> retrievedTestUser = userRepository.findById(id);

        //Then
        Assertions.assertTrue(retrievedTestUser.isPresent());

        //CleanUp
        userRepository.deleteById(id);
    }

}