using HotelBooking.Data;
using HotelBooking.Models;
using HotelBooking.Services;
using HotelBooking.Specs.Integrations;
using Microsoft.EntityFrameworkCore.ChangeTracking;
using Xunit;

namespace HotelBooking.Specs.IntegrationTests;

public class RoomServiceTest : IDisposable
{
    private readonly RoomService _roomService;
    private readonly HotelContext _context;

    // Is run before each test
    public RoomServiceTest()
    {
        _context ??= HotelContextConfig.CreateInMemoryContext();
        _context.Database.EnsureCreated();
        _roomService = new RoomService(_context);
    }

    // Is run after each test
    public void Dispose()
    {
        _context.Database.EnsureDeleted();
    }

    [Fact]
    public async void RetrieveRoomFromDbContext()
    {
        RoomType expectedRoomType = RoomType.Basic;
        Room newRoom = new Room(expectedRoomType);
        EntityEntry<Room> createdRoom = await _context.Rooms.AddAsync(newRoom);
        await _context.SaveChangesAsync();
        
        Room? retrievedRoom = await _roomService.GetRoomById(createdRoom.Entity.Id);
        Assert.NotNull(retrievedRoom);
        
        RoomType? actualRoomType = retrievedRoom?.Type;
        Assert.Equal(expectedRoomType, actualRoomType);
    }
}