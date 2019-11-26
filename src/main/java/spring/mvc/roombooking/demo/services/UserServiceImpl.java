package spring.mvc.roombooking.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.entities.User;
import spring.mvc.roombooking.demo.exceptions.UserAlreadyExistException;
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

    public UserDto postUser(User newUser){
        if (!repository.findById(newUser.getLogin()).isPresent()){
            newUser.setPassword(this.setPassword(newUser.getPassword()));
            repository.save(newUser);
            return this.convertToDto(newUser);
        }else{
            throw new UserAlreadyExistException(newUser.getLogin());
        }
    }

    public UserDto getUser(String login){
        if(repository.findById(login).isPresent())
            return this.convertToDto(repository.findById(login).get());
        else
            throw new UserNotFoundException(login);
    }

    public UserDto updateUser(User newUser, String login){
        if (repository.findById(login).isPresent()){
            User user = this.convertFromDto(this.getUser(login));
            user.setName(newUser.getName());
            user.setSurname(newUser.getSurname());
            user.setLogin(newUser.getLogin());
            user.setPassword(this.setPassword(newUser.getPassword()));
            repository.save(user);
            return this.convertToDto(user);
        }else {
            throw new UserNotFoundException(login);
        }
    }

    public void deleteUser(String login){
        if (repository.findById(login).isPresent())
            repository.deleteById(login);
        else
            throw new UserNotFoundException(login);
    }

    private UserDto convertToDto(User user){
        return new UserDto(user.getName(),user.getSurname(),user.getLogin());
    }
    public User convertFromDto(UserDto userDto){
        if (repository.findById(userDto.getLogin()).isPresent())
            return repository.findById(userDto.getLogin()).get();
        else
            throw new UserNotFoundException(userDto.getLogin());
    }
    private String setPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
