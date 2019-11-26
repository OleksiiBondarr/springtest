package spring.mvc.roombooking.demo.dto;

import lombok.Data;

@Data
public class BookingPassDto {
    private String login;
    private String roomName;
    private String password;
    private String fromdate;
    private String todate;
    public BookingPassDto(){}
    public BookingPassDto(String login, String roomName, String password, String fromdate, String todate){
        this.login = login;
        this.roomName = roomName;
        this.fromdate = fromdate;
        this.todate = todate;
        this.password = password;
    }
}
