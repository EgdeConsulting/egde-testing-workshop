package no.egde.hotelbooking.services;

import no.egde.hotelbooking.data.RoomRepository;
import no.egde.hotelbooking.models.Customer;
import no.egde.hotelbooking.models.Room;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Inject
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        Iterable<Room> rooms = roomRepository.findAll();
        return StreamSupport.stream(rooms.spliterator(), false).collect(Collectors.toList());
    }

    public Optional<Room> getRoomById(int id) {
        return roomRepository.findById(id);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
}
