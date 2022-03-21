package no.egde.hotelbooking.services;

import no.egde.hotelbooking.data.RoomRepository;
import no.egde.hotelbooking.models.Room;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomsService {
    private final RoomRepository roomRepository;

    @Inject
    public RoomsService(RoomRepository roomRepository) {
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

    public List<Room> takeMostPopularRooms(int count) {
        Iterable<Room> rooms = roomRepository.findAll();
        return StreamSupport.stream(rooms.spliterator(), false)
                .sorted(Comparator.comparing(Room::getRoomPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }
}
