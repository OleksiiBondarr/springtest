package spring.mvc.roombooking.demo.Services;

import spring.mvc.roombooking.demo.Entities.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User postUser(User newUser);
    User getUser(String login);
    User updateUser(User newUser, String login);
    void deleteUser(String login);
}
