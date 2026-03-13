package com.hotel.booking.model;

/**
 * Suite Room class - inherits from Room
 */
public class SuiteRoom extends Room {

    public SuiteRoom(double price) {
        super("Suite", 3, price);
    }

    @Override
    public String getRoomDetails() {
        return "Room Type: " + getRoomType() +
                ", Beds: " + getNumberOfBeds() +
                ", Price: $" + getPrice();
    }
}