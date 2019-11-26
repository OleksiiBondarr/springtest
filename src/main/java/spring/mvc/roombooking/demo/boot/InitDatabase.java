package spring.mvc.roombooking.demo.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import spring.mvc.roombooking.demo.entities.Room;
import spring.mvc.roombooking.demo.entities.User;
import spring.mvc.roombooking.demo.repositories.RoomRepository;
import spring.mvc.roombooking.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Component
public class InitDatabase {
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private Resource users;

    private Resource rooms;
    @Autowired
    public InitDatabase(UserRepository userRepository, RoomRepository roomRepository, ResourceLoader resourceLoader) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.users = resourceLoader.getResource("classpath:users");
        this.rooms = resourceLoader.getResource("classpath:rooms");
    }

    @PostConstruct
    public void initDatabase()
     {
         try {
             File fileUsers = users.getFile();
             File fileRooms = rooms.getFile();
             for (Scanner s = new Scanner(fileUsers); s.hasNextLine(); ) {
                 String[] arrOfStr = s.nextLine().split(", ", 4);
                 userRepository.save(new User(arrOfStr[0],arrOfStr[1],arrOfStr[2],arrOfStr[3]));
             }
             for (Scanner s = new Scanner(fileRooms); s.hasNextLine(); ) {
                 String[] arrOfStr = s.nextLine().split(", ", 5);
                 if (arrOfStr.length == 4)
                    roomRepository.save(new Room(arrOfStr[0],arrOfStr[1],Integer.parseInt(arrOfStr[2]),arrOfStr[3].equals("yes"),""));
                 else
                    roomRepository.save(new Room(arrOfStr[0],arrOfStr[1],Integer.parseInt(arrOfStr[2]),arrOfStr[3].equals("yes"),arrOfStr[4]));
             }
         }
         catch (IOException ex){
             ex.printStackTrace();
         }
    }
}
