package no.egde.hotelbooking.controllers;

import no.egde.hotelbooking.models.Customer;
import no.egde.hotelbooking.models.CustomerWithTotalPayment;
import no.egde.hotelbooking.services.CustomersService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customers")
public class CustomersController {

    private final CustomersService customersService;

    @Inject
    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customersService.getCustomers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Customer getCustomerById(int id) {
        Optional<Customer> customerOptional = customersService.getCustomerById(id);

        if (customerOptional.isEmpty()) {
            throw new Error("Couldn't find customer");
        }

        return customerOptional.get();
    }

    @PostMapping(value = "/createCustomer", consumes = "application/json", produces = "application/json")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customersService.createCustomer(customer);
    }

    @GetMapping(value = "/takeHighestPayingCustomer/{count}", produces = "application/json")
    public List<CustomerWithTotalPayment> takeHighestPayingCustomer(@PathVariable int count) {
        return customersService.takeHighestPayingCustomers(count);
    }
}
