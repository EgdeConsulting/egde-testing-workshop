package no.egde.hotelbooking.services;

import no.egde.hotelbooking.data.BookingRepository;
import no.egde.hotelbooking.models.Booking;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Inject
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getBookings() {
        Iterable<Booking> bookings = bookingRepository.findAll();
        return StreamSupport.stream(bookings.spliterator(), false).collect(Collectors.toList());
    }

    public Optional<Booking> getBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public Booking postBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> takeBookingsWithLargeBill(int count) {
        return Collections.emptyList();
    }
}
