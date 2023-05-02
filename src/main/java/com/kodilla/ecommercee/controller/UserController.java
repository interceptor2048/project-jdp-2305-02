package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    //mock users data =========================
    List<UserDto> userDtoList = new ArrayList<UserDto>() {
        {
            add(new UserDto(1L,"kchasteau0",1,1684));
            add(new UserDto(2L,"acaldecutt1",1,5980));
            add(new UserDto(3L,"cclemetts2",1,6473));
            add(new UserDto(4L,"broget3",0,5736));
            add(new UserDto(5L,"mrany4",1,6024));
        }
    };
    //=========================================

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) {
        for (UserDto userDto : userDtoList) {
            if (userDto.getUserId() == id) {
                return ResponseEntity.ok(userDto);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        for (UserDto userDto : userDtoList) {
            if (userDto.getUserId() == id) {
                userDtoList.remove(userDto);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path="/updateUser/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDtoIn) {
        for (UserDto userDto : userDtoList) {
            if (userDto.getUserId() == id) {
                userDtoList.remove(userDto);
                userDtoList.add(userDtoIn);
                return ResponseEntity.ok(userDtoIn);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        userDtoList.add(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/blockUser/{id}")
    public ResponseEntity<Void> blockUser(@PathVariable("id") long id) {
        for (UserDto userDto : userDtoList) {
            if (userDto.getUserId() == id) {
                if(userDto.getStatus()!=0) {
                    userDto.setStatus(0);
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.badRequest().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/generateKey/{id}")
    public ResponseEntity<Integer> generateKey(@PathVariable("id") long id) {
        for (UserDto userDto : userDtoList) {
            if (userDto.getUserId() == id) {
                Random random = new Random();
                int key = random.nextInt(8999)+1000;
                userDto.setUserKey(key);
                return ResponseEntity.ok(key);
            }
        }
        return ResponseEntity.notFound().build();
    }


}
