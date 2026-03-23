package com.hotel.booking.service.concurrent;

import com.hotel.booking.model.Reservation;
import com.hotel.booking.inventory.RoomInventory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentBookingProcessor {

    private RoomInventory inventory;
    private Queue<Reservation> bookingQueue = new ConcurrentLinkedQueue<>();

    public ConcurrentBookingProcessor(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void submitBooking(Reservation r) {
        bookingQueue.add(r);
    }

    public void processBookings() {
        bookingQueue.parallelStream().forEach(r -> {
            synchronized (inventory) {
                if (inventory.isAvailable(r.getRoomType())) {
                    inventory.updateAvailability(r.getRoomType(), inventory.getAvailability(r.getRoomType()) - 1);
                    System.out.println("✅ Booked: " + r);
                } else {
                    System.out.println("❌ No availability for: " + r);
                }
            }
        });
    }
}