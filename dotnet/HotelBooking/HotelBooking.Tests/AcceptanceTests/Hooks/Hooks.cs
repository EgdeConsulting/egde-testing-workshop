using BoDi;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc.Testing;

namespace HotelBooking.Specs.Hooks;

[Binding]
public class Hooks
{
    private readonly IObjectContainer _objectContainer;
    
    public Hooks(IObjectContainer objectContainer)
    {
        _objectContainer = objectContainer;
    }

    [BeforeScenario]
    public async Task RegisterServices()
    {
        var factory = new WebApplicationFactory<Program>()
            .WithWebHostBuilder(builder =>
            {
                builder.UseEnvironment("Testing");
            });
        _objectContainer.RegisterInstanceAs(factory);
    }
}