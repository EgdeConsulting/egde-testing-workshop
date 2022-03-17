namespace HotelBooking.Models;

public class Room
{
    private static readonly Dictionary<RoomType, int> Prices = new Dictionary<RoomType, int>
    {
        { RoomType.Suite, 10000 },
        { RoomType.Business, 5000 },
        { RoomType.Standard, 2500 },
        { RoomType.Basic, 1000 }
    };

    public Room(RoomType type)
    {
        Type = type;
    }

    public Room() {}

    public int Id { get; set; }
    public RoomType Type { get; set; }
    public List<Booking> Bookings { get; set; } = new();

    public int RoomPrice => Prices[Type];

    public int TotalEarnings ()  => Bookings.Sum(booking => booking.Bill);
}

public enum RoomType
{
    Suite, Business, Standard, Basic
}

