package com.hotel.booking.app;

import com.hotel.booking.model.*;
import com.hotel.booking.inventory.RoomInventory;
import com.hotel.booking.service.SearchService;

import java.util.Arrays;
import java.util.List;

/**
 * Hotel Booking Application
 * UC1 – UC4 combined: Application entry, Room modeling, Inventory, Room search
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

        // Example update: booking 1 single room
        inventory.updateAvailability(singleRoom.getRoomType(), 9);
        System.out.println("\nAfter booking 1 Single room:");
        inventory.displayInventory();

        // ---------- UC4: Room Search ----------
        SearchService searchService = new SearchService(inventory);
        searchService.displayAvailableRooms(allRooms);

        // Example: mark Double room as fully booked
        inventory.updateAvailability(doubleRoom.getRoomType(), 0);
        System.out.println("\nAfter all Double rooms are booked:");
        searchService.displayAvailableRooms(allRooms);
    }
}