package com.hotel.booking.app;

import com.hotel.booking.model.*;
import com.hotel.booking.inventory.RoomInventory;
import com.hotel.booking.service.addon.*;
import com.hotel.booking.service.history.*;
import com.hotel.booking.service.validation.*;
import com.hotel.booking.service.cancellation.*;

public class HostelBookingApplication {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Hotel Booking System ");
        System.out.println(" Application Version : v1.0 ");
        System.out.println("=================================");

        try {

            // ==============================
            // UC2: Rooms
            // ==============================
            Room singleRoom = new SingleRoom(50);
            Room doubleRoom = new DoubleRoom(80);
            Room suiteRoom = new SuiteRoom(150);

            System.out.println("\nAvailable Rooms:");
            System.out.println(singleRoom.getRoomDetails());
            System.out.println(doubleRoom.getRoomDetails());
            System.out.println(suiteRoom.getRoomDetails());

            // ==============================
            // UC3: Inventory
            // ==============================
            RoomInventory inventory = new RoomInventory();

            BookingValidator.validateRoomType(singleRoom.getRoomType());
            BookingValidator.validateRoomType(doubleRoom.getRoomType());
            BookingValidator.validateRoomType(suiteRoom.getRoomType());

            inventory.addRoomType(singleRoom.getRoomType(), 10);
            inventory.addRoomType(doubleRoom.getRoomType(), 5);
            inventory.addRoomType(suiteRoom.getRoomType(), 2);

            inventory.displayInventory();

            // Example update with validation
            BookingValidator.validateInventory(9);
            inventory.updateAvailability(singleRoom.getRoomType(), 9);

            System.out.println("\nAfter booking 1 Single room:");
            inventory.displayInventory();

            // ==============================
            // UC7: Add-On Services
            // ==============================
            AddOnServiceManager addOnManager = new AddOnServiceManager();
            String reservationId = "R001";

            addOnManager.addService(reservationId, new AddOnService("Breakfast", 20));
            addOnManager.addService(reservationId, new AddOnService("Airport Pickup", 50));

            System.out.println("\nServices for Reservation " + reservationId + ":");
            for (AddOnService s : addOnManager.getServices(reservationId)) {
                System.out.println(s);
            }
            System.out.println("Total Add-On Cost: $" +
                    addOnManager.calculateTotalCost(reservationId));

            // ==============================
            // UC8: Booking History
            // ==============================
            BookingHistory bookingHistory = new BookingHistory();

            Reservation r1 = new Reservation("R001", "Single");
            Reservation r2 = new Reservation("R002", "Double");

            bookingHistory.addReservation(r1);
            bookingHistory.addReservation(r2);

            BookingReportService reportService = new BookingReportService();
            reportService.printReport(bookingHistory);

            // ==============================
            // UC9: Validation Demo
            // ==============================
            // Validation already applied above for room type & inventory

            // ==============================
            // UC10: Cancellation Demo
            // ==============================
            System.out.println("\n=== Cancellation Demo ===");

            CancellationService cancellationService = new CancellationService(inventory);

            // Add dummy reservations
            cancellationService.addReservation(r1);
            cancellationService.addReservation(r2);

            // Cancel one reservation
            cancellationService.cancelReservation("R001");

            // Attempt to cancel a non-existent reservation
            cancellationService.cancelReservation("R999");

            // Display remaining rollback stack
            System.out.println("Remaining reservations in rollback stack: " +
                    cancellationService.getRollbackStack());

        } catch (InvalidBookingException e) {
            System.out.println("\n❌ Booking Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n⚠ Unexpected Error: " + e.getMessage());
        }
    }
}