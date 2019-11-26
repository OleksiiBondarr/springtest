package spring.mvc.roombooking.demo.entities;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Booking {
    private @Id
    @GeneratedValue
    Long id;
    private String login;
    private String roomName;
    private Date fromdate;
    private Date todate;

    public Booking() {
    }

    public Booking(String login, String roomName, Date fromdate, Date todate) {
        this.login = login;
        this.roomName = roomName;
        this.fromdate = fromdate;
        this.todate = todate;
    }
}
