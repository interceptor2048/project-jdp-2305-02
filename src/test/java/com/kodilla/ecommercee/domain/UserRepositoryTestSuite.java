package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;
    private final User user1 = new User(null, "kchasteau0", 1, 1684);
    private final User user2 = new User(null, "acaldecutt1", 1, 5980);
    private final User user3 = new User(null, "cclemetts2", 0, 6473);
    private Long id1;
    private Long id2;
    private Long id3;

    @BeforeEach
    void setUp() {
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        id1 = user1.getId();
        id2 = user2.getId();
        id3 = user3.getId();
    }

    @AfterEach
    void cleanUp() {
        try {
            userRepository.deleteById(id1);
            userRepository.deleteById(id2);
            userRepository.deleteById(id3);
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @Test
    void test_getUsers() {
        //Given

        //When

        //Then
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    void test_getUser_ValidUser() {
        //Given

        //When
        Optional<User> userFromRepository = userRepository.findById(id1);

        //Then
        assertEquals(user1.getId(), userFromRepository.get().getId());
        assertEquals(user1.getUsername(), userFromRepository.get().getUsername());
        assertEquals(user1.getStatus(), userFromRepository.get().getStatus());
        assertEquals(user1.getUserKey(), userFromRepository.get().getUserKey());
    }

    @Test
    void test_getUser_InvalidUser() {
        //Given

        //When
        Optional<User> userFromRepository = userRepository.findById(999L);

        //Then
        assertFalse(userFromRepository.isPresent());
    }

    @Test
    void test_deleteUserById_ValidUser() {
        //Given

        //When
        userRepository.deleteById(3L);

        //Then
        assertEquals(Optional.empty(), userRepository.findById(3L));
    }

    @Test
    void test_updateUserById_ValidUser() {
        //Given
         User modificatedUser = new User(id2, "acaldecutt1_mod", 1, 5980);

        //When
        userRepository.save(modificatedUser);
        Optional<User> userFromRepository = userRepository.findById(id2);

        //Then
        assertEquals(modificatedUser.getId(), userFromRepository.get().getId());
        assertEquals(modificatedUser.getUsername(), userFromRepository.get().getUsername());
        assertEquals(modificatedUser.getStatus(), userFromRepository.get().getStatus());
        assertEquals(modificatedUser.getUserKey(), userFromRepository.get().getUserKey());
    }

    @Test
    void test_createUser() {
        //Given
        User user = new User(null, "broget3", 0, 5736);

        //When
        userRepository.save(user);
        Long id = user.getId();
        Optional<User> userFromRepository = userRepository.findById(id);

        //Then
        assertEquals(user.getId(), userFromRepository.get().getId());
        assertEquals(user.getUsername(), userFromRepository.get().getUsername());
        assertEquals(user.getStatus(), userFromRepository.get().getStatus());
        assertEquals(user.getUserKey(), userFromRepository.get().getUserKey());
        userRepository.deleteById(id);
    }

    @Test
    void test_blockUserById_ValidUser() {
        //Given
        User blockedUser = new User(user3.getId(), user3.getUsername(), 0, user3.getStatus());

        //When
        userRepository.save(blockedUser);
        Optional<User> userFromRepository = userRepository.findById(id3);

        //Then
        assertEquals(blockedUser.getId(), userFromRepository.get().getId());
        assertEquals(blockedUser.getUsername(), userFromRepository.get().getUsername());
        assertEquals(0, userFromRepository.get().getStatus());
        assertEquals(blockedUser.getUserKey(), userFromRepository.get().getUserKey());
    }
}
