#nullable disable
using Microsoft.AspNetCore.Mvc;
using HotelBooking.Models;
using HotelBooking.Services;

namespace HotelBooking.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class BookingsController : ControllerBase
    {
        private readonly IBookingService _bookingService;

        public BookingsController(IBookingService bookingService)
        {
            _bookingService = bookingService;
        }
        
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Booking>>> GetBookings()
        {
            var bookings = await _bookingService.GetBookings();
            return Ok(bookings);
        }
        
        [HttpGet("{id}")]
        public async Task<ActionResult<Booking>> GetBookingById(int id)
        {
            var booking = await _bookingService.GetBookingById(id);
            return booking == null ? NotFound("Booking was not found") : Ok(booking);
        }
        
        [HttpPost]
        public async Task<ActionResult<Booking>> PostBooking(Booking booking)
        {
            var createdBooking = await _bookingService.PostBooking(booking);
            return Ok(createdBooking);
        }

        [HttpGet("largestbilling/{count}")]
        public async Task<ActionResult<List<Booking>>> TakeBookingsWithLargesBill(int count)
        {
            var bookingsWithLargesBill = await _bookingService.TakeBookingsWithLargestBill(count);
            return Ok(bookingsWithLargesBill);
        }
    }
}
