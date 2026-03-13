package com.hotel.booking.service;

import com.hotel.booking.model.Room;
import com.hotel.booking.inventory.RoomInventory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to search for available rooms
 */
public class SearchService {

    private RoomInventory inventory;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Get all available rooms from the list
     * @param rooms List of Room objects
     * @return List of rooms with availability > 0
     */
    public List<Room> getAvailableRooms(List<Room> rooms) {
        return rooms.stream()
                .filter(room -> inventory.getAvailability(room.getRoomType()) > 0)
                .collect(Collectors.toList());
    }

    /**
     * Display available rooms with details
     */
    public void displayAvailableRooms(List<Room> rooms) {
        List<Room> availableRooms = getAvailableRooms(rooms);
        System.out.println("\n--- Available Rooms ---");
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available at the moment.");
        } else {
            for (Room room : availableRooms) {
                System.out.println(room.getRoomDetails() +
                        " | Available: " + inventory.getAvailability(room.getRoomType()));
            }
        }
    }
}