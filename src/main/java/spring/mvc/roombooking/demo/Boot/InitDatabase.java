package spring.mvc.roombooking.demo.Boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.mvc.roombooking.demo.Entities.Room;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.Repositories.RoomRepository;
import spring.mvc.roombooking.demo.Repositories.UserRepository;

import javax.annotation.PostConstruct;

@Component
public class InitDatabase {
    private UserRepository userRepository;
    private RoomRepository roomRepository;

    @Autowired
    public InitDatabase(UserRepository userRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @PostConstruct
    public void initDatabase()//TODO create file
     {
        userRepository.save(new User("Bilbo", "Baggins","bil","bo"));
        userRepository.save(new User("Frodo", "Baggins","fro","do"));
        roomRepository.save(new Room("Large Room","1st floor", 100, true,"22-22-22-22"));
        roomRepository.save(new Room("Medium Room","2st floor", 10, false,""));
    }
}
