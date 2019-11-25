package spring.mvc.roombooking.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.mvc.roombooking.demo.Entities.Room;
import spring.mvc.roombooking.demo.Services.RoomService;
import spring.mvc.roombooking.demo.dto.RoomDto;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    @Autowired
    RoomController( RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping()
    List<RoomDto> getRooms() {
        return roomService.getRooms();
    }

    @PostMapping
    RoomDto postRoom(@RequestBody Room newRoom) {
        return roomService.postRoom(newRoom);
    }

    @PutMapping("/{name}")
    RoomDto updateUser(@RequestBody Room newRoom, @PathVariable String name) {
        return roomService.updateRoom(newRoom, name);
    }

    @DeleteMapping("/{name}")
    void deleteRoom(@PathVariable String name) {
        roomService.deleteRoom(name);
    }
}