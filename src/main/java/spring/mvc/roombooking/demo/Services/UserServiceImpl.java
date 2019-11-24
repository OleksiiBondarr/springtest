package spring.mvc.roombooking.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.Exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.Repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    public List<User> getUsers(){
        return repository.findAll();
    }
    public User postUser(User newUser){
        return repository.save(newUser);
    }
    public User getUser(Long id){
       return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    public User updateUser(User newUser, Long id){
       return repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setRole(newUser.getRole());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }
    public void deleteUser(Long id){
        repository.deleteById(id);
    }

}
