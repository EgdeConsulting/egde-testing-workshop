package no.egde.hotelbooking.data;

import java.util.List;

import no.egde.hotelbooking.models.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findByCustomerId(int customerId);
}
