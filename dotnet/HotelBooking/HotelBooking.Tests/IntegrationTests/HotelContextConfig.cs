using HotelBooking.Data;
using Microsoft.EntityFrameworkCore;

namespace HotelBooking.Specs.Integrations;

public class HotelContextConfig
{
    public static HotelContext CreateInMemoryContext()
    {
        return new HotelContext(new DbContextOptionsBuilder<HotelContext>()
            .UseInMemoryDatabase("hotel-integration-testing")
            .Options
        );
    }
}