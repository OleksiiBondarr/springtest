package spring.mvc.roombooking.demo.Services;

import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto postUser(User newUser);
    UserDto getUser(String login);
    UserDto updateUser(User newUser, String login);
    void deleteUser(String login);
    User convertFromDto(UserDto userDto);
}
