package com.hotel.booking.app;

import com.hotel.booking.model.*;
import com.hotel.booking.inventory.RoomInventory;

public class HostelBookingApplication {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Hotel Booking System ");
        System.out.println(" Application Version : v1.0 ");
        System.out.println("=================================");

        // UC2: Rooms
        Room singleRoom = new SingleRoom(50);
        Room doubleRoom = new DoubleRoom(80);
        Room suiteRoom = new SuiteRoom(150);

        System.out.println("\nAvailable Rooms:");
        System.out.println(singleRoom.getRoomDetails());
        System.out.println(doubleRoom.getRoomDetails());
        System.out.println(suiteRoom.getRoomDetails());

        // UC3: Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType(singleRoom.getRoomType(), 10);
        inventory.addRoomType(doubleRoom.getRoomType(), 5);
        inventory.addRoomType(suiteRoom.getRoomType(), 2);

        inventory.displayInventory();

        // Example update
        inventory.updateAvailability(singleRoom.getRoomType(), 9);
        System.out.println("\nAfter booking 1 Single room:");
        inventory.displayInventory();
    }
}