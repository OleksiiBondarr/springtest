package spring.mvc.roombooking.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.roombooking.demo.entities.User;

public interface UserRepository extends  JpaRepository<User, String> {
}
