package spring.mvc.roombooking.demo.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Room {
    private @Id
    String name;
    private String location;
    private Integer numberOfSeats;
    private boolean projector;
    private String phoneNumber;

    public Room() {
    }

    public Room(String name, String location, Integer numberOfSeats, boolean projector, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.numberOfSeats = numberOfSeats;
        this.projector = projector;
        this.phoneNumber = phoneNumber;

    }
}
