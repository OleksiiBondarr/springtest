package spring.mvc.roombooking.demo.Services;

import spring.mvc.roombooking.demo.Entities.Room;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.dto.RoomDto;
import spring.mvc.roombooking.demo.dto.UserDto;

import java.util.List;

public interface RoomService {
    List<RoomDto> getRooms();
    RoomDto postRoom(Room newRoom);
    RoomDto updateRoom(Room newRoom, String name);
    void deleteRoom(String name);
}
