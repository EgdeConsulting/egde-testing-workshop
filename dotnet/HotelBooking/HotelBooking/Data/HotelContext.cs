#nullable disable
using Microsoft.EntityFrameworkCore;
using HotelBooking.Models;

namespace HotelBooking.Data
{
    public class HotelContext : DbContext
    {
        public HotelContext(DbContextOptions options) : base(options) { }
        public DbSet<Customer> Customers { get; set; }
        public DbSet<Room> Rooms { get; set; }
        public DbSet<Booking> Bookings { get; set; }
    }
}
