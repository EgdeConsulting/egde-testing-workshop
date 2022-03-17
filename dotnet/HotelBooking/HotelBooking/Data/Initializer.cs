using HotelBooking.Data;
using HotelBooking.Models;

namespace HotelBooking;

public class Initializer
{
    public static void InitDb(HotelContext context)
    {
        context.Database.EnsureDeleted();
        context.Database.EnsureCreated();
        
        var customers = Enumerable
            .Range(0, 10)
            .Select(_ => CreateFakeCustomer());
        context.Customers.AddRange(customers);
        
        var rooms = Enumerable
            .Range(0, 10)
            .Select(_ => CreateFakeRoom());
        context.Rooms.AddRange(rooms);
        
        context.SaveChanges();
        
        var bookingList = customers.SelectMany((_, customerId) =>
        {
            var randomBookingsCount = new Random().Next(1, 10);
            return Enumerable
                .Range(1, randomBookingsCount)
                .Select(_ =>
                {
                    var roomId = new Random().Next(1, rooms.Count());
                    return CreateFakeBooking(customerId + 1, roomId);
                });
        });
        
        context.Bookings.AddRange(bookingList);
        context.SaveChanges();
    }

    private static Customer CreateFakeCustomer() =>
        new()
        {
            Email = Faker.Internet.Email(),
            Name = Faker.Name.FullName(),
        };

    private static Room CreateFakeRoom() =>
        new()
        {
            Type = Faker.Enum.Random<RoomType>()
        };

    private static Booking CreateFakeBooking(int customerId, int roomId)
    {
        var date = Faker.Identification.DateOfBirth();
        var daysBooked = new Random().Next(1, 10);
        return new Booking
        {
            CustomerId = customerId,
            RoomId = roomId,
            CheckIn = date,
            CheckOut = date.AddDays(daysBooked),
        };
    }
}