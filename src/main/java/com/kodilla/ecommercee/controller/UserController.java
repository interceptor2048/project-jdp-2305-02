package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> userList = userDbService.getUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(userList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) {
        Optional<User> user = userDbService.getUser(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(userMapper.mapToUserDto(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        Optional<User> user = userDbService.getUser(id);
        if(user.isPresent()) {
            userDbService.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path="/updateUser/{id}", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {
        Optional<User> user = userDbService.getUser(id);
        if (user.isPresent()) {
            userDbService.updateUser(userMapper.mapToUser(userDto));
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        Optional<User> user = userDbService.getUser(userDto.getId());
        if (!user.isPresent()) {
            userDbService.createUser(userMapper.mapToUser(userDto));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/blockUser/{id}")
    public ResponseEntity<Void> switchBlockadeOfUser(@PathVariable("id") long id) {
        Optional<User> user = userDbService.getUser(id);
        if(user.isPresent()) {
            if (user.get().getStatus() == 0) {
                user.get().unblockUser();
                userDbService.updateUser(user.get());
                return ResponseEntity.ok().build();
            } else if (user.get().getStatus() == 1) {
                user.get().blockUser();
                userDbService.updateUser(user.get());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/generateKey/{id}")
    public ResponseEntity<Integer> generateKey(@PathVariable("id") long id) {
        Optional<User> user = userDbService.getUser(id);
        if (user.isPresent()) {
            Random random = new Random();
            int key = random.nextInt(8999) + 1000;
            user.get().setUserKey(key);
            userDbService.updateUser(user.get());
            return ResponseEntity.ok(key);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
