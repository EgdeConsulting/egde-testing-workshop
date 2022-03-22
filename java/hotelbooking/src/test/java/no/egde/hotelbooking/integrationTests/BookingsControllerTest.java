package no.egde.hotelbooking.integrationTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import no.egde.hotelbooking.controllers.BookingsController;
import no.egde.hotelbooking.models.Booking;
import no.egde.hotelbooking.models.Customer;
import no.egde.hotelbooking.models.Room;
import no.egde.hotelbooking.models.RoomType;
import no.egde.hotelbooking.services.BookingsService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookingsController.class)
public class BookingsControllerTest {

    @Inject
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingsService bookingsService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldReturnBookings() throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(2022, 3, 22, 15, 15 ,15);

        Room room = new Room();
        room.setRoomType(RoomType.BUSINESS);
        room.setId(1);

        Customer customer = new Customer();
        customer.setEmail("email.no");
        customer.setId(1);
        customer.setName("Navn Navnesen");

        Booking booking = new Booking();
        booking.setId(1);
        booking.setCheckIn(dateTime);
        booking.setCheckOut(dateTime.plusHours(2));
        booking.setStayLength(2);
        booking.setRoom(room);
        booking.setCustomer(customer);

        when(bookingsService.getBookings()).thenReturn(Collections.singletonList(booking));

        MvcResult result = mockMvc
            .perform(MockMvcRequestBuilders.get("/bookings/").accept("application/json"))
            .andExpect(status().isOk())
            .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("[{\"id\":1,\"checkIn\":\"2022-03-22T15:15"
            + ":15\",\"checkOut\":\"2022-03-22T17:15:15\",\"customer\":{\"id\":1,\"name\":\"Navn Navnesen\","
            + "\"email\":\"email.no\"},\"room\":{\"id\":1,\"roomType\":\"BUSINESS\",\"roomPrice\":5000},"
            + "\"stayLength\":2,\"bill\":10000}]");
    }
}
