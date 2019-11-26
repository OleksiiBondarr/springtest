package spring.mvc.roombooking.demo.services;

import spring.mvc.roombooking.demo.entities.Room;
import spring.mvc.roombooking.demo.dto.RoomDto;

import java.util.List;

public interface RoomService {
    List<RoomDto> getRooms();

    RoomDto getRoom(String name);

    RoomDto postRoom(RoomDto newRoom);

    RoomDto updateRoom(RoomDto newRoom);

    void deleteRoom(String name);
}
