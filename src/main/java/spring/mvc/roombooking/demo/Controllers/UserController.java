package spring.mvc.roombooking.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.Exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.Repositories.UserRepository;
import spring.mvc.roombooking.demo.Services.UserService;
import spring.mvc.roombooking.demo.Services.UserServiceImpl;
@RestController
public class UserController {

    private final UserService userService;

    UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    User postUser(@RequestBody User newUser) {
        return userService.postUser(newUser);
    }

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id) {

        return userService.getUser(id);
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {

        return userService.updateUser(newUser, id);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}