package com.hotel.booking.model;

/**
 * Double Room class - inherits from Room
 */
public class DoubleRoom extends Room {

    public DoubleRoom(double price) {
        super("Double", 2, price);
    }

    @Override
    public String getRoomDetails() {
        return "Room Type: " + getRoomType() +
                ", Beds: " + getNumberOfBeds() +
                ", Price: $" + getPrice();
    }
}