package no.egde.hotelbooking.models;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Room {
    private static final Map<RoomType, Integer> prices = new HashMap<>(Map.ofEntries(
        Map.entry(RoomType.SUITE, 10000),
        Map.entry(RoomType.BUSINESS, 5000),
        Map.entry(RoomType.STANDARD, 2500),
        Map.entry(RoomType.BASIC, 1000)
    ));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public Room(RoomType type) {
        this.roomType = type;
    }

    public Room() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomPrice() {
        return prices.get(roomType);
    }
}
