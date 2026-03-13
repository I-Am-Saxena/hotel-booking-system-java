package com.hotel.booking.app;

import com.hotel.booking.model.*;
import com.hotel.booking.inventory.RoomInventory;
import com.hotel.booking.service.SearchService;
import com.hotel.booking.service.BookingService;
import com.hotel.booking.reservation.Reservation;

import java.util.*;

/**
 * Hotel Booking Application
 * UC1 – UC6 combined: Entry, Room modeling, Inventory, Search, Booking Queue, Allocation
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

        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Double"));
        bookingQueue.add(new Reservation("Charlie", "Suite"));
        bookingQueue.add(new Reservation("David", "Single"));
        bookingQueue.add(new Reservation("Eve", "Double"));

        System.out.println("\n--- Booking Request Queue (FIFO) ---");
        for (Reservation res : bookingQueue) {
            System.out.println(res);
        }

        // ---------- UC6: Reservation Confirmation & Room Allocation ----------
        BookingService bookingService = new BookingService(inventory);
        System.out.println("\n--- Processing Reservations ---");
        bookingService.processReservations(bookingQueue);

        System.out.println("\n--- Updated Room Inventory After Allocation ---");
        inventory.displayInventory();

        System.out.println("\n--- Search Available Rooms After Allocation ---");
        searchService.displayAvailableRooms(allRooms);
    }
}