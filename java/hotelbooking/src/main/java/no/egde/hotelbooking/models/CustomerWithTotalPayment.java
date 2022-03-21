package no.egde.hotelbooking.models;

public class CustomerWithTotalPayment {
    private Customer customer;
    private Integer amount;

    public CustomerWithTotalPayment(Customer customer, Integer amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
