package spring.mvc.roombooking.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Data
public class UserDto {
        private String name;
        private String surname;
        private String login;
        public UserDto() {}

        public UserDto(String name, String surname, String login) {
            this.name = name;
            this.surname = surname;
            this.login = login;
        }

}
