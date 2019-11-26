package spring.mvc.roombooking.demo.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String surname;
    private String login;

    public UserDto() {
    }

    public UserDto(String name, String surname, String login) {
        this.name = name;
        this.surname = surname;
        this.login = login;
    }

}
