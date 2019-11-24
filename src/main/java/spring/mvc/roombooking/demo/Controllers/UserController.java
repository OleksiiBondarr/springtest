package spring.mvc.roombooking.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.Exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.Repositories.UserRepository;
import spring.mvc.roombooking.demo.Services.UserService;
import spring.mvc.roombooking.demo.Services.UserServiceImpl;
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    UserController( UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    User postUser(@RequestBody User newUser) {
        return userService.postUser(newUser);
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {

        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}