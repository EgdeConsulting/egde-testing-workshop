package no.egde.hotelbooking.controllers;

import no.egde.hotelbooking.models.Room;
import no.egde.hotelbooking.services.RoomsService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rooms")
public class RoomsController {

    private final RoomsService roomsService;

    @Inject
    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomsService.getRooms();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Room getRoomById(@PathVariable int id) throws Exception {
        Optional<Room> roomOptional = roomsService.getRoomById(id);

        if (roomOptional.isEmpty()) {
            throw new Exception("Couldn't find room");
        }

        return roomOptional.get();
    }

    @PostMapping(value = "/createRoom", consumes = "application/json", produces = "application/json")
    public Room createRoom(@RequestBody Room room) {
        return roomsService.createRoom(room);
    }

    @GetMapping(value = "/takeMostPopularRooms/{count}", produces = "application/json")
    public List<Room> takeMostPopularRooms(@PathVariable int count) {
        return roomsService.takeMostPopularRooms(count);
    }
}
