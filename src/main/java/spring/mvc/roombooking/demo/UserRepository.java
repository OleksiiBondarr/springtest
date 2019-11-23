package spring.mvc.roombooking.demo;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends  JpaRepository<User, Long> {
}
