package spring.mvc.roombooking.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.entities.User;
import spring.mvc.roombooking.demo.exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.repositories.UserRepository;
import spring.mvc.roombooking.demo.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    @Autowired
    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }
    public List<UserDto> getUsers(){
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public UserDto postUser(User newUser){//TODO check for unique
        newUser.setPassword(this.setPassword(newUser.getPassword()));
        repository.save(newUser);
        return this.convertToDto(newUser);
    }
    public UserDto getUser(String login){
        List<User> users = repository.findAll().stream().filter(user -> user.getLogin().equals(login)).collect(Collectors.toList());
        if (users.size()==1){
            return this.convertToDto(users.get(0));
        }
        else {
            throw new UserNotFoundException(login);
        }
    }
    public UserDto updateUser(User newUser, String login){
        User user = this.convertFromDto(this.getUser(login));
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setLogin(newUser.getLogin());
        user.setPassword(this.setPassword(newUser.getPassword()));
        repository.save(user);
        return this.convertToDto(user);
    }
    public void deleteUser(String login){
        User user = this.convertFromDto(getUser(login));
        repository.deleteById(user.getId());
    }

    private UserDto convertToDto(User user){
        return new UserDto(user.getName(),user.getSurname(),user.getLogin());
    }
    public User convertFromDto(UserDto userDto){
        return repository.findAll().stream().filter(user -> user.getLogin().equals(userDto.getLogin())).collect(Collectors.toList()).get(0);
    }
    private String setPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
