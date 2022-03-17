namespace HotelBooking.Models;

public class Customer
{
    public int Id { get; set; }
    
    public string Name { get; set; }
    
    public string Email { get; set; }
    
    public List<Booking> Bookings { get; set; } = new();
}

public class CustomerWithTotalPayment
{
    public Customer Customer { get; set; }
    public int Amount { get; set; }
}