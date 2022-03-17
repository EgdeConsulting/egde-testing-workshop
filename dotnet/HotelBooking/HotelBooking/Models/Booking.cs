using System.ComponentModel.DataAnnotations;

namespace HotelBooking.Models;

public class Booking
{
    public int Id { get; set; }
    
    public DateTime CheckIn { get; set; }
    
    public DateTime CheckOut { get; set; }
    
    [System.Text.Json.Serialization.JsonIgnore]
    public Customer? Customer { get; set; }
    
    public int CustomerId { get; set; }
    
    [System.Text.Json.Serialization.JsonIgnore]
    public Room? Room { get; set; }
    
    public int RoomId { get; set; }

    public int StayLength
    {
        get
        {
            var stayLength = (CheckOut - CheckIn).Days;
            return stayLength > 1 ? stayLength : 1;
        }
    }
    
    public int Bill => StayLength * Room?.RoomPrice ?? 0;
}