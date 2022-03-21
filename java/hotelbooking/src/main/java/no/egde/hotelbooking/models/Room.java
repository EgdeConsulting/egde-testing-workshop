package no.egde.hotelbooking.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private static final Map<RoomType, Integer> prices = new HashMap<>(Map.ofEntries(
        Map.entry(RoomType.SUITE, 10000),
        Map.entry(RoomType.BUSINESS, 5000),
        Map.entry(RoomType.STANDARD, 2500),
        Map.entry(RoomType.BASIC, 1000)
    ));

    private int id;
    private RoomType roomType;
    private List<Booking> bookings;

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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getRoomPrice(RoomType type) {
        return prices.get(type);
    }

    public int getTotalEarnings(RoomType roomType) {
        return getBookings().stream()
                .mapToInt(item -> item.getBill(roomType))
                .sum();
    }
}
