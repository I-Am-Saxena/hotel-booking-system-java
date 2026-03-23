package com.hotel.booking.app;

import com.hotel.booking.model.*;
import com.hotel.booking.inventory.RoomInventory;
import com.hotel.booking.service.addon.*;
import com.hotel.booking.service.history.*;

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

        // ==============================
        // UC7: Add-On Services
        // ==============================

        System.out.println("\n=== Add-On Services Demo ===");

        AddOnServiceManager addOnManager = new AddOnServiceManager();
        String reservationId = "R001";

        addOnManager.addService(reservationId, new AddOnService("Breakfast", 20));
        addOnManager.addService(reservationId, new AddOnService("Airport Pickup", 50));

        System.out.println("Services for Reservation " + reservationId + ":");
        for (AddOnService service : addOnManager.getServices(reservationId)) {
            System.out.println(service);
        }

        System.out.println("Total Add-On Cost: $" +
                addOnManager.calculateTotalCost(reservationId));

        // ==============================
        // UC8: Booking History
        // ==============================

        BookingHistory bookingHistory = new BookingHistory();

        // Dummy reservations (since your earlier UC creates them)
        Reservation r1 = new Reservation("R001", "Single");
        Reservation r2 = new Reservation("R002", "Double");

        bookingHistory.addReservation(r1);
        bookingHistory.addReservation(r2);

        BookingReportService reportService = new BookingReportService();
        reportService.printReport(bookingHistory);
    }
}