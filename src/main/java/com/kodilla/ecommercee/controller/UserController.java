package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userMapper.mapToUserDtoList(userService.getUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(userService.getUser(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path="/updateUser/{id}", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) throws UserNotFoundException {
        userService.updateUser(id, userMapper.mapToUser(userDto));
        return ResponseEntity.ok(userMapper.mapToUserDto(userService.getUser(userDto.getId())));
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userMapper.mapToUser(userDto));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/switchBlockade/{id}")
    public ResponseEntity<Void> switchBlockade(@PathVariable("id") long id) {
        userService.switchBlockade(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/generateKey/{id}")
    public ResponseEntity<Integer> generateKey(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.generateKey(id));
    }
}
