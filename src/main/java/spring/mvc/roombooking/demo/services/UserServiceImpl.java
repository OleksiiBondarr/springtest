package spring.mvc.roombooking.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.dto.UserPassDto;
import spring.mvc.roombooking.demo.entities.User;
import spring.mvc.roombooking.demo.exceptions.PasswordIsToShortException;
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
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDto> getUsers() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto postUser(UserPassDto newUser) {
        if (!repository.findById(newUser.getLogin()).isPresent()) {
            if (newUser.getPassword().length() < 6)
                throw new PasswordIsToShortException();
            newUser.setPassword(this.setPassword(newUser.getPassword()));
            User user = new User(
                    newUser.getName(),
                    newUser.getSurname(),
                    newUser.getLogin(),
                    newUser.getPassword()
            );
            repository.save(user);
            return this.convertToDto(user);
        } else {
            throw new UserAlreadyExistException(newUser.getLogin());
        }
    }

    @Override
    public UserDto getUser(String login) {
        if (repository.findById(login).isPresent())
            return this.convertToDto(repository.findById(login).get());
        else
            throw new UserNotFoundException(login);
    }

    @Override
    public UserDto updateUser(UserPassDto newUser) {
        if (repository.findById(newUser.getLogin()).isPresent()) {
            if (newUser.getPassword().length() < 6)
                throw new PasswordIsToShortException();
            User user = this.convertFromDto(this.getUser(newUser.getLogin()));
            user.setName(newUser.getName());
            user.setSurname(newUser.getSurname());
            user.setPassword(this.setPassword(newUser.getPassword()));
            repository.save(user);
            return this.convertToDto(user);
        } else {
            throw new UserNotFoundException(newUser.getLogin());
        }
    }

    @Override
    public void deleteUser(String login) {
        if (repository.findById(login).isPresent())
            repository.deleteById(login);
        else
            throw new UserNotFoundException(login);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getName(), user.getSurname(), user.getLogin());
    }

    @Override
    public User convertFromDto(UserDto userDto) {
        if (repository.findById(userDto.getLogin()).isPresent())
            return repository.findById(userDto.getLogin()).get();
        else
            throw new UserNotFoundException(userDto.getLogin());
    }

    private String setPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
