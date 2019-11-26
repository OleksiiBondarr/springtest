package spring.mvc.roombooking.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import spring.mvc.roombooking.demo.entities.Booking;
import spring.mvc.roombooking.demo.repositories.BookingRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AvailabilityServiceImplTest {
    @MockBean(name = "userRepository")
    private BookingRepository bookingRepository;
    @InjectMocks
    private AvailabilityServiceImpl availabilityService;

    @BeforeEach
    public void setUp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Booking booking1 = new Booking("login", "name", simpleDateFormat.parse("2019-11-25 8:15:00"), simpleDateFormat.parse("2019-11-26 10:15:00"));
            Booking booking2 = new Booking("login1", "name", simpleDateFormat.parse("2019-11-25 12:15:00"), simpleDateFormat.parse("2019-11-26 13:15:00"));
            List<Booking> bookings = Arrays.asList(booking1, booking2);
            when(bookingRepository.findAll()).thenReturn(bookings);

            availabilityService = new AvailabilityServiceImpl(bookingRepository);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void isAvailable() {
        boolean busy = availabilityService.isAvailable("name", "2019-11-25 9:15:00", "2019-11-25 9:16:00");
        boolean busy1 = availabilityService.isAvailable("name", "2019-11-25 7:15:00", "null");
        boolean busy2 = availabilityService.isAvailable("name", "null", "2019-11-25 12:16:00");
        boolean free = availabilityService.isAvailable("name", "2019-11-25 10:16:00", "2019-11-25 11:16:00");
        boolean free1 = availabilityService.isAvailable("name", "2019-11-25 14:15:00", "null");
        boolean free2 = availabilityService.isAvailable("name", "null", "2019-11-25 7:16:00");
        assertThat(free);
        assertThat(free1);
        assertThat(free2);
        assertThat(!busy);
        assertThat(!busy1);
        assertThat(!busy2);
    }

    @Test
    void convertStringToDate() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datestring = "2019-11-25 9:15:00";
            Date date = simpleDateFormat.parse(datestring);
            assertEquals(date, availabilityService.convertStringToDate(datestring));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void isAvailableNow() {
        boolean free = availabilityService.isAvailableNow("name");
        assertThat(free);
    }
}