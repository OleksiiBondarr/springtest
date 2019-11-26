package spring.mvc.roombooking.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingDto {
    private String login;
    private String roomName;
    private String fromdate;
    private String todate;
    public BookingDto(){}
    public BookingDto(String login, String roomName, String fromdate, String todate){
        this.login = login;
        this.roomName = roomName;
        this.fromdate = fromdate;
        this.todate = todate;
    }
}
