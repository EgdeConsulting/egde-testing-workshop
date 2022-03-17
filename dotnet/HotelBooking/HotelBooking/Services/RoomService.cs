using HotelBooking.Data;
using HotelBooking.Models;
using Microsoft.EntityFrameworkCore;

namespace HotelBooking.Services;

public interface IRoomService
{
    Task<Room> CreateRoom(Room room);
    Task<Room?> GetRoomById(int id);
    Task<List<Room>> GetRooms();

    List<Room> TakeMostPopularRooms(int count);
    Task<Dictionary<int, int>> GetRoomEarnings();
    Task<int?> GetEarningsByRoomId(int id);
}

public class RoomService : IRoomService
{
    private readonly HotelContext _context;
    public RoomService(HotelContext context)
    {
        _context = context;
    }
    public async Task<Room> CreateRoom(Room room)
    {
        var createdRoom = await _context
            .Rooms
            .AddAsync(room);
        await _context.SaveChangesAsync();
        return createdRoom.Entity;
    }

    public async Task<Room?> GetRoomById(int id)
    {
        return await _context
            .Rooms
            .Include(room => room.Bookings)
            .Where(room => room.Id == id)
            .FirstOrDefaultAsync();
    }

    public async Task<List<Room>> GetRooms()
    {
        return await _context.Rooms.ToListAsync();
    }

    public List<Room>TakeMostPopularRooms(int count)
    {
        var rooms = _context
            .Rooms
            .OrderBy(CountNumberOfDaysBooked)
            .TakeLast(count)
            .Reverse()
            .ToList();
        
        return rooms;
    }

    public async Task<Dictionary<int, int>> GetRoomEarnings()
    {
        var rooms = await _context
            .Rooms
            .Include(room => room.Bookings)
            .ToDictionaryAsync(x => x.Id, x => x.TotalEarnings());
        
        return rooms;
    }

    public async Task<int?> GetEarningsByRoomId(int id)
    {
        var room = await _context
            .Rooms
            .Include(room => room.Bookings)
            .SingleOrDefaultAsync(room => room.Id == id);
        
        return room?.TotalEarnings();
    }

    private int CountNumberOfDaysBooked(Room room) =>
        room.Bookings.Sum(booking => booking.StayLength);

}