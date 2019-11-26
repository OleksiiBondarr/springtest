package spring.mvc.roombooking.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.roombooking.demo.entities.Room;

public interface RoomRepository extends JpaRepository<Room, String> {
}
