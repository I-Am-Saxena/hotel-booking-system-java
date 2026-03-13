package com.hotel.booking.app;

import com.hotel.booking.model.*;
import com.hotel.booking.inventory.RoomInventory;
import com.hotel.booking.service.SearchService;
import com.hotel.booking.reservation.Reservation;

import java.util.*;

/**
 * Hotel Booking Application
 * UC1 – UC5 combined: Entry, Room modeling, Inventory, Search, Booking Queue
 */
public class HotelBookingApplication {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Hotel Booking System ");
        System.out.println(" Application Version : v1.0 ");
        System.out.println("=================================");

        // ---------- UC2: Rooms ----------
        Room singleRoom = new SingleRoom(50);
        Room doubleRoom = new DoubleRoom(80);
        Room suiteRoom = new SuiteRoom(150);

        List<Room> allRooms = Arrays.asList(singleRoom, doubleRoom, suiteRoom);

        System.out.println("\n--- Room Details ---");
        for (Room room : allRooms) {
            System.out.println(room.getRoomDetails());
        }

        // ---------- UC3: Room Inventory ----------
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType(singleRoom.getRoomType(), 10);
        inventory.addRoomType(doubleRoom.getRoomType(), 5);
        inventory.addRoomType(suiteRoom.getRoomType(), 2);

        System.out.println("\n--- Room Inventory ---");
        inventory.displayInventory();

        // ---------- UC4: Room Search ----------
        SearchService searchService = new SearchService(inventory);
        searchService.displayAvailableRooms(allRooms);

        // ---------- UC5: Booking Request Queue ----------
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Sample booking requests
        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Double"));
        bookingQueue.add(new Reservation("Charlie", "Suite"));
        bookingQueue.add(new Reservation("David", "Single"));

        System.out.println("\n--- Booking Request Queue (FIFO) ---");
        for (Reservation res : bookingQueue) {
            System.out.println(res);
        }

        System.out.println("\nProcessing requests in FIFO order...");
        while (!bookingQueue.isEmpty()) {
            Reservation res = bookingQueue.poll();
            System.out.println("Processing reservation: " + res);
        }
    }
}