package spring.mvc.roombooking.demo.dto;

import lombok.Data;

@Data
public class UserPassDto {
    private String name;
    private String surname;
    private String login;
    private String password;

    public UserPassDto() {
    }

    public UserPassDto(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
}
