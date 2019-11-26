package spring.mvc.roombooking.demo.dto;

import lombok.Data;

@Data
public class RoomDto {

    private String name;
    private String location;
    private Integer numberOfSeats;
    private boolean projector;
    private String phoneNumber;

    public RoomDto() {}
    public RoomDto(String name, String location, Integer numberOfSeats, boolean projector, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.numberOfSeats = numberOfSeats;
        this.projector = projector;
        this.phoneNumber = phoneNumber;
    }
}