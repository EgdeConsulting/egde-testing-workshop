using System.Net.Http.Json;
using HotelBooking.Models;
using Microsoft.AspNetCore.Mvc.Testing;
using Newtonsoft.Json;
using TechTalk.SpecFlow.Assist;
using Xunit;

namespace HotelBooking.Specs.Steps;

[Binding]
public class RoomSteps
{
    private readonly HttpClient _httpClient;
    private readonly ScenarioContext _scenarioContext;
    
    public RoomSteps(ScenarioContext scenarioContext, WebApplicationFactory<Program> factory)
    {
        _scenarioContext = scenarioContext;
        _httpClient = factory.CreateClient();
    }
    
    [Given(@"rooms with the following details exists")]
    public async Task GivenRoomsWithTheFollowingDetailsExists(Table table)
    {
        var rooms = table.CreateSet<Room>().ToList();
        rooms.ForEach(async room =>
        {
            await _httpClient.PostAsJsonAsync("/rooms", room);
        });
    }
    
    [When(@"a user request all rooms")]
    public async Task WhenAUserRequestAllRooms()
    {
        var jsonContent = await _httpClient.GetStringAsync("/rooms");
        var roomList = JsonConvert.DeserializeObject<List<Room>>(jsonContent);
        _scenarioContext.Add("RoomListCount", roomList?.Count);
    }
    
    [Then(@"A list containing (.*) elements is retrieved")]
    public void ThenAListContainingElementsIsRetrieved(int expectedCount)
    {
        var actualCount = _scenarioContext.Get<int>("RoomListCount");
        Assert.Equal(expectedCount, actualCount);
    }
}