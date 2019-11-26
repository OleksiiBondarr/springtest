package spring.mvc.roombooking.demo.services;

import spring.mvc.roombooking.demo.entities.Room;
import spring.mvc.roombooking.demo.dto.RoomDto;

import java.util.List;

public interface RoomService {
    List<RoomDto> getRooms();
    RoomDto getRoom(String name);
    RoomDto postRoom(Room newRoom);
    RoomDto updateRoom(Room newRoom, String name);
    void deleteRoom(String name);
}
