package no.egde.hotelbooking.services;

import no.egde.hotelbooking.data.CustomerRepository;
import no.egde.hotelbooking.models.Customer;
import no.egde.hotelbooking.models.CustomerWithTotalPayment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Inject
    public CustomerService(CustomerRepository customerRepository) {
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
        return Collections.emptyList();
    }
}
