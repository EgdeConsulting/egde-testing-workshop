package no.egde.hotelbooking.unitTests;

import no.egde.hotelbooking.models.Booking;
import no.egde.hotelbooking.models.Customer;
import no.egde.hotelbooking.models.Room;
import no.egde.hotelbooking.services.BookingsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookingsControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private BookingsService bookingsService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldReturnBookings() throws Exception {
        Booking booking = new Booking();
        booking.setId(1);
        booking.setCheckIn(LocalDateTime.now());
        booking.setCheckOut(LocalDateTime.now().plusHours(2));
        booking.setStayLength(2);
        booking.setRoom(new Room());
        booking.setCustomer(new Customer());

        when(bookingsService.getBookings()).thenReturn(Collections.singletonList(booking));

        mockMvc.perform(MockMvcRequestBuilders.get(pathEqualTo("/rooms/"))).andExpect(status().isOk());
    }
}
