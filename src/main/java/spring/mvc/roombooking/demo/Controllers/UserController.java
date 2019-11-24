package spring.mvc.roombooking.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.Exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.Repositories.UserRepository;
import spring.mvc.roombooking.demo.Services.UserService;
import spring.mvc.roombooking.demo.Services.UserServiceImpl;
import spring.mvc.roombooking.demo.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    UserController( UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    UserDto postUser(@RequestBody User newUser) {
        return userService.postUser(newUser);
    }

    @GetMapping("/{login}")
    UserDto getUser(@PathVariable String login) {
        return userService.getUser(login);
    }

    @PutMapping("/{login}")
    UserDto updateUser(@RequestBody User newUser, @PathVariable String login) {
        return userService.updateUser(newUser, login);
    }

    @DeleteMapping("/{login}")
    void deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
    }
}