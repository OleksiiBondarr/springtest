package spring.mvc.roombooking.demo.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.roombooking.demo.Entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
