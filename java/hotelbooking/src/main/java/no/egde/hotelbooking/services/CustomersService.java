package no.egde.hotelbooking.services;

import no.egde.hotelbooking.data.CustomerRepository;
import no.egde.hotelbooking.models.Booking;
import no.egde.hotelbooking.models.Customer;
import no.egde.hotelbooking.models.CustomerWithTotalPayment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomersService {

    private final CustomerRepository customerRepository;

    @Inject
    public CustomersService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        Iterable<Customer> customers = customerRepository.findAll();
        return StreamSupport.stream(customers.spliterator(), false).collect(Collectors.toList());
    }
    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<CustomerWithTotalPayment> takeHighestPayingCustomers(int count) {
        Iterable<Customer> customers = customerRepository.findAll();
        return StreamSupport.stream(customers.spliterator(), false)
                .map(e -> new CustomerWithTotalPayment(e, e.getBookings().stream().mapToInt(Booking::getBill).sum()))
                .sorted(Comparator.comparing(CustomerWithTotalPayment::getAmount).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }
}
