package com.hotel.booking.model;

/**
 * Abstract Room class representing a generalized room.
 * Cannot be instantiated directly.
 */
public abstract class Room {

    private String roomType;
    private int numberOfBeds;
    private double price;

    // Constructor
    public Room(String roomType, int numberOfBeds, double price) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
    }

    // Getters
    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPrice() {
        return price;
    }

    // Abstract method for room description
    public abstract String getRoomDetails();
}