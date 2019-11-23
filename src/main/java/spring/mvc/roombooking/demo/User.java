package spring.mvc.roombooking.demo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
class User {

    private @Id @GeneratedValue Long id;
    @Setter(AccessLevel.PUBLIC)private String name;
    private String role;

    User() {}

    User(String name, String role) {
        this.name = name;
        this.role = role;
    }
}