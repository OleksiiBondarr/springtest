package spring.mvc.roombooking.demo.Entities;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Room {
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String location;
    private Integer numberOfSeats;
    private boolean projector;
    private String phoneNumber;
    private boolean available = true;

    public Room() {}
    public Room(String name, String location, Integer numberOfSeats, boolean projector, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.numberOfSeats = numberOfSeats;
        this.projector = projector;
        this.phoneNumber = phoneNumber;

    }
}
