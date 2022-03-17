using HotelBooking.Data;
using HotelBooking.Models;
using Microsoft.EntityFrameworkCore;

namespace HotelBooking.Services;

public interface ICustomerService
{
    Task<List<Customer>> GetCustomers();
    Task<Customer?> GetCustomerById(int id);
    Task<Customer> CreateCustomer(Customer customer);
    Task<List<CustomerWithTotalPayment>> TakeHighestPayingCustomers(int count);
}

public class CustomerService : ICustomerService
{
    private readonly HotelContext _context;

    public CustomerService(HotelContext context)
    {
        _context = context;
    }
    
    public async Task<List<Customer>> GetCustomers()
    {
        return await _context.Customers.ToListAsync();;
    }

    public async Task<Customer?> GetCustomerById(int id)
    {
        return await _context
            .Customers
            .Include(customer => customer.Bookings)
            .Where(customer => customer.Id == id)
            .FirstOrDefaultAsync();
    }

    public async Task<Customer> CreateCustomer(Customer customer)
    {
        var createdCustomer = await _context.Customers.AddAsync(customer);
        await _context.SaveChangesAsync();
        return createdCustomer.Entity;
    }

    public async Task<List<CustomerWithTotalPayment>> TakeHighestPayingCustomers(int count)
    {
        var customers = await _context
            .Customers
            .Include(customer => customer.Bookings)
            .ThenInclude(booking => booking.Room)
            .ToListAsync();
        
        return customers
            .Select(customer =>
            {
                var amount = customer
                    .Bookings
                    .Sum(booking => booking.Bill);
                customer.Bookings = new List<Booking>();
                return new CustomerWithTotalPayment
                {
                    Customer = customer,
                    Amount = amount
                };
            })
            .OrderBy(x => x.Amount)
            .TakeLast(count)
            .Reverse()
            .ToList();

    }
}