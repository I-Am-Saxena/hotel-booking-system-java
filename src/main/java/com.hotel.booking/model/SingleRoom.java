package com.hotel.booking.model;

/**
 * Single Room class - inherits from Room
 */
public class SingleRoom extends Room {

    public SingleRoom(double price) {
        super("Single", 1, price);
    }

    @Override
    public String getRoomDetails() {
        return "Room Type: " + getRoomType() +
                ", Beds: " + getNumberOfBeds() +
                ", Price: $" + getPrice();
    }
}