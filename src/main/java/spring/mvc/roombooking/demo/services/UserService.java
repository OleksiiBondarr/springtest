package spring.mvc.roombooking.demo.services;

import spring.mvc.roombooking.demo.dto.UserPassDto;
import spring.mvc.roombooking.demo.entities.User;
import spring.mvc.roombooking.demo.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto postUser(UserPassDto newUser);

    UserDto getUser(String login);

    UserDto updateUser(UserPassDto newUser);

    void deleteUser(String login);

    User convertFromDto(UserDto userDto);
}
