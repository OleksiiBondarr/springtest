package spring.mvc.roombooking.demo.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.roombooking.demo.Entities.User;

public interface UserRepository extends  JpaRepository<User, Long> {

}
