package com.hotel.booking.service.cancellation;

import com.hotel.booking.model.Reservation;
import com.hotel.booking.inventory.RoomInventory;

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class CancellationService {

    private RoomInventory inventory;
    private Stack<String> rollbackStack = new Stack<>();
    private Map<String, Reservation> reservations = new HashMap<>();

    public CancellationService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void addReservation(Reservation r) {
        reservations.put(r.getReservationId(), r);
        rollbackStack.push(r.getReservationId());
    }

    public boolean cancelReservation(String reservationId) {
        if (!reservations.containsKey(reservationId)) {
            System.out.println("❌ Reservation does not exist: " + reservationId);
            return false;
        }

        // Rollback inventory
        Reservation r = reservations.get(reservationId);
        inventory.incrementAvailability(r.getRoomType());

        // Remove from reservations
        reservations.remove(reservationId);
        rollbackStack.remove(reservationId);

        System.out.println("✅ Reservation cancelled: " + reservationId);
        return true;
    }

    public Stack<String> getRollbackStack() {
        return rollbackStack;
    }
}