package spring.mvc.roombooking.demo.Services;

import spring.mvc.roombooking.demo.Entities.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User postUser(User newUser);
    User getUser(Long id);
    User getUserByLogin(String login);
    User updateUser(User newUser, Long id);
    void deleteUser(Long id);
}
