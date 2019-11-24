package spring.mvc.roombooking.demo.Boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.Repositories.UserRepository;

import javax.annotation.PostConstruct;

@Component
public class InitDatabase {
    private UserRepository userRepository;

    @Autowired
    public InitDatabase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initDatabase() {
        userRepository.save(new User("Bilbo Baggins", "burglar"));
        userRepository.save(new User("Frodo Baggins", "thief"));
    }
}
