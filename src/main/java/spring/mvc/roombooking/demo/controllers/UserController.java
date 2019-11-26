package spring.mvc.roombooking.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.mvc.roombooking.demo.dto.UserPassDto;
import spring.mvc.roombooking.demo.entities.User;
import spring.mvc.roombooking.demo.services.UserService;
import spring.mvc.roombooking.demo.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    UserDto postUser(@RequestBody UserPassDto newUser) {
        return userService.postUser(newUser);
    }

    @PutMapping("/{login}")
    UserDto updateUser(@RequestBody UserPassDto newUser) {
        return userService.updateUser(newUser);
    }

    @DeleteMapping("/{login}")
    HttpStatus deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
        return HttpStatus.OK;
    }
}