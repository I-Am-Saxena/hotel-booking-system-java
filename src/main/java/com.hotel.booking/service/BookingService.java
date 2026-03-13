package com.hotel.booking.service;

import com.hotel.booking.inventory.RoomInventory;
import com.hotel.booking.reservation.Reservation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

/**
 * Booking service that confirms reservations and allocates rooms
 */
public class BookingService {

    private RoomInventory inventory;
    // Map<RoomType, Set of allocated room IDs>
    private Map<String, Set<String>> allocatedRooms;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        this.allocatedRooms = new HashMap<>();
    }

    /**
     * Process all reservations in FIFO order
     */
    public void processReservations(Queue<Reservation> bookingQueue) {
        while (!bookingQueue.isEmpty()) {
            Reservation res = bookingQueue.poll();
            String roomType = res.getRequestedRoomType();

            if (inventory.getAvailability(roomType) > 0) {
                String roomId = generateRoomId(roomType);
                allocatedRooms.computeIfAbsent(roomType, k -> new HashSet<>()).add(roomId);
                inventory.updateAvailability(roomType, inventory.getAvailability(roomType) - 1);

                System.out.println("Reservation confirmed for " + res.getGuestName() +
                        " | Room Type: " + roomType + " | Room ID: " + roomId);
            } else {
                System.out.println("Reservation failed for " + res.getGuestName() +
                        " | Room Type: " + roomType + " | No rooms available.");
            }
        }
    }

    /**
     * Generate unique room ID
     */
    private String generateRoomId(String roomType) {
        int nextId = allocatedRooms.getOrDefault(roomType, new HashSet<>()).size() + 1;
        return roomType.substring(0, 1).toUpperCase() + nextId;
    }
}