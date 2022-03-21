package no.egde.hotelbooking.controllers;

import no.egde.hotelbooking.models.Booking;
import no.egde.hotelbooking.services.BookingsService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bookings")
public class BookingsController {

    private final BookingsService bookingsService;

    @Inject
    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @GetMapping
    public List<Booking> getBookings() {
        return bookingsService.getBookings();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Booking getBookingById(@PathVariable int id) throws Exception {
        Optional<Booking> bookingOptional = bookingsService.getBookingById(id);

        if (bookingOptional.isEmpty()) {
            throw new Exception("Feil!");
        }

        return bookingOptional.get();
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Booking postBooking(@RequestBody Booking booking) {
        return bookingsService.postBooking(booking);
    }

    @GetMapping(value = "/takeBookingsWithLargestBill/{count}", produces = "application/json")
    public List<Booking> takeBookingsWithLargestBill(@PathVariable int count) {
        return bookingsService.takeBookingsWithLargestBill(count);
    }
}
