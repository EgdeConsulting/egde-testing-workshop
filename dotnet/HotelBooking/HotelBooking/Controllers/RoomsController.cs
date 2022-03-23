#nullable disable
using Microsoft.AspNetCore.Mvc;
using HotelBooking.Models;
using HotelBooking.Services;

namespace HotelBooking.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class RoomsController : ControllerBase
    {
        private readonly IRoomService _roomService;

        public RoomsController(IRoomService roomService)
        {
            _roomService = roomService;
        }
        
        [HttpGet]
        public async Task<ActionResult<List<Room>>> GetRooms()
        {
            var rooms = await _roomService.GetRooms();
            return Ok(rooms);
        }
        
        [HttpGet("{id}")]
        public async Task<ActionResult<Room>> GetRoomById(int id)
        {
            var room = await _roomService.GetRoomById(id);
            return room == null ? NotFound("Room id was not found") : Ok(room);
        }
        
        [HttpPost]
        public async Task<ActionResult<Room>> CreateRoom(Room room)
        {
            var createdRoom = await _roomService.CreateRoom(room);
            return Ok(createdRoom);
        }

        [HttpGet("popular/{count}")]
        public ActionResult<Room> TakeMostPopularRooms(int count)
        {
            var mostPopularRooms = _roomService.TakeMostPopularRooms(count);
            return Ok(mostPopularRooms);
        }
        
        [HttpGet("earnings")]
        public async Task<ActionResult<Dictionary<int, int>>> GetRoomEarnings()
        {
            var earningsByRoom = await _roomService.GetRoomEarnings();
            return Ok(earningsByRoom);
        }
        
        [HttpGet("earnings/{id}")]
        public async Task<ActionResult<int>> GetEarningsByRoomId(int id)
        {
            var earnings = await _roomService.GetEarningsByRoomId(id);
            return earnings == null ? NotFound("Room id was not found") : Ok(earnings);
        }

        [HttpGet("available/today")]
        public async Task<ActionResult<List<Room>>> GetAvailableRoomsToday()
        {
            var availableRooms = await _roomService.GetAvailableRoomsToday();
            return Ok(availableRooms);
        }

    }
}
