package no.egde.hotelbooking.models;

import java.time.LocalDateTime;

public class Booking {
    private int id;
    private LocalDateTime checkIn;
    private LocalDateTime CheckOut;
    private Customer customer;
    private Room room;
    private int stayLength;
    private int bill;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        CheckOut = checkOut;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getStayLength() {
        return stayLength;
    }

    public void setStayLength(int stayLength) {
        this.stayLength = stayLength;
    }

    public int getBill(RoomType roomType) {
        return getStayLength() * getRoom().getRoomPrice(roomType);
    }
}
