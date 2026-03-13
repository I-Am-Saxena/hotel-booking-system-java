package com.hotel.booking.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Centralized inventory management for hotel rooms
 */
public class RoomInventory {

    // Map to store room type -> available count
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Initialize inventory for a room type
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Get current availability for a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability after booking or cancellation
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type " + roomType + " does not exist.");
        }
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("\n--- Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
    }
}