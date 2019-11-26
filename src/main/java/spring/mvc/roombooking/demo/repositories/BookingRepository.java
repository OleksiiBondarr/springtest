package spring.mvc.roombooking.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.roombooking.demo.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
