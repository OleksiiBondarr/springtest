package spring.mvc.roombooking.demo.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Data
public class User {
    private String name;
    private String surname;
    private @Id String login;
    private String password;
    public User() {}

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

}