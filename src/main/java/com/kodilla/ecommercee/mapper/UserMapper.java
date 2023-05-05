package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getStatus(),
                userDto.getUserKey()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getStatus(),
                user.getUserKey()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList());
    }

    public List<User> mapToUserList(final List<UserDto> userDtoList) {
        return userDtoList.stream().map(user -> mapToUser(user)).collect(Collectors.toList());
    }
}
