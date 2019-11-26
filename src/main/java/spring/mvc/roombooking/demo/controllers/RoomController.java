package spring.mvc.roombooking.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.mvc.roombooking.demo.entities.Room;
import spring.mvc.roombooking.demo.services.RoomService;
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


    @GetMapping
    List<RoomDto> getRooms() {
        return roomService.getRooms();
    }

    @PostMapping
    RoomDto postRoom(@RequestBody RoomDto newRoom) {
        return roomService.postRoom(newRoom);
    }

    @PutMapping("/{name}")
    RoomDto updateUser(@RequestBody RoomDto newRoom) {
        return roomService.updateRoom(newRoom);
    }

    @DeleteMapping("/{name}")
    HttpStatus deleteRoom(@PathVariable String name) {
        roomService.deleteRoom(name);
        return HttpStatus.OK;
    }
}