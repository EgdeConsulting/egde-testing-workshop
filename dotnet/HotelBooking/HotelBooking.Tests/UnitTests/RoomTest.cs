using HotelBooking.Models;
using Xunit;

namespace HotelBooking.Specs.Units;

public class RoomTest
{
    [Fact]
    public void CreateRoom()
    {
        RoomType expectedRoomType = RoomType.Basic;
        Room room = new Room(expectedRoomType);
        RoomType actualRoomType = room.Type;
        Assert.NotNull(room);
        Assert.Equal(expectedRoomType, actualRoomType);
    }
}