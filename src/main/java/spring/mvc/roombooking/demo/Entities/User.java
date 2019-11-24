package spring.mvc.roombooking.demo.Entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Data
public class User {

    private @Id @GeneratedValue Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    public User() {}

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public void setPassword(String password){
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

}