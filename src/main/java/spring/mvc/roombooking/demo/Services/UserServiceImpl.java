package spring.mvc.roombooking.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.Exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.Repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }
    public List<User> getUsers(){
        return repository.findAll();
    }
    public User postUser(User newUser){
        return repository.save(newUser);
    }
    public User getUser(String login){
        List<User> users = repository.findAll().stream().filter(user -> user.getLogin().equals(login)).collect(Collectors.toList());
        if (users.size()==1){
            return users.get(0);
        }
        else {
            throw new UserNotFoundException(login);
        }
    }
    public User updateUser(User newUser, String login){
        List<User> users = repository.findAll().stream().filter(user -> user.getLogin().equals(login)).collect(Collectors.toList());
        User user;
        if (users.size()==1){
            user = users.get(0);
        }
        else {
            throw new UserNotFoundException(login);
        }
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setLogin(newUser.getLogin());
        user.setPasswordWithoutEncoding(newUser.getPassword());
        return repository.save(user);
    }
    public void deleteUser(String login){
        User user = getUser(login);
        repository.deleteById(user.getId());
    }
}
