using HotelBooking.Data;
using HotelBooking.Models;
using Microsoft.EntityFrameworkCore;

namespace HotelBooking.Services;

public interface IBookingService
{
    Task<IEnumerable<Booking>> GetBookings();
    Task<Booking?> GetBookingById(int id);
    Task<Booking> PostBooking(Booking booking);
    Task<List<Booking>> TakeBookingsWithLargesBill(int count);
}

public class BookingService : IBookingService
{
    private readonly HotelContext _context;

    public BookingService(HotelContext context)
    {
        _context = context;
    }
    
    public async Task<IEnumerable<Booking>> GetBookings()
    {
        var bookings = await _context
            .Bookings
            .Include(x => x.Customer)
            .Include(x => x.Room)
            .ToListAsync();
        return bookings;
    }

    public async Task<Booking?> GetBookingById(int id)
    {
        return await _context.Bookings.FindAsync(id);
    }

    public async Task<Booking> PostBooking(Booking booking)
    {
        var createdBooking = await _context
            .Bookings
            .AddAsync(booking);
        await _context.SaveChangesAsync();
        return createdBooking.Entity;
    }

    public async Task<List<Booking>> TakeBookingsWithLargesBill(int count)
    {
        var bookings = await _context
            .Bookings
            .Include(booking => booking.Room)
            .ToListAsync();

        return bookings
            .OrderBy(booking => booking.Bill)
            .TakeLast(count)
            .Reverse()
            .ToList();
    }
}