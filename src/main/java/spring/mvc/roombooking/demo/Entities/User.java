package spring.mvc.roombooking.demo.Entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
public class User {

    private @Id @GeneratedValue Long id;
    @Setter(AccessLevel.PUBLIC)private String name;
    private String role;

    public User() {}

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }
}