package com.hotel.booking.app;

import com.hotel.booking.model.*;
import com.hotel.booking.inventory.RoomInventory;
import com.hotel.booking.service.addon.*;
import com.hotel.booking.service.history.*;
import com.hotel.booking.service.validation.*;
import com.hotel.booking.service.cancellation.*;
import com.hotel.booking.service.concurrent.*;
import com.hotel.booking.service.persistence.*;

import java.util.List;

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
            // UC10: Cancellation Demo
            // ==============================
            System.out.println("\n=== Cancellation Demo ===");

            CancellationService cancellationService = new CancellationService(inventory);

            cancellationService.addReservation(r1);
            cancellationService.addReservation(r2);

            cancellationService.cancelReservation("R001");
            cancellationService.cancelReservation("R999");

            System.out.println("Remaining reservations in rollback stack: " +
                    cancellationService.getRollbackStack());

            // ==============================
            // UC11: Concurrency Demo
            // ==============================
            System.out.println("\n=== Concurrency Demo ===");

            ConcurrentBookingProcessor concurrentProcessor = new ConcurrentBookingProcessor(inventory);

            Reservation r3 = new Reservation("R003", "Single");
            Reservation r4 = new Reservation("R004", "Single");
            Reservation r5 = new Reservation("R005", "Double");

            concurrentProcessor.submitBooking(r3);
            concurrentProcessor.submitBooking(r4);
            concurrentProcessor.submitBooking(r5);

            concurrentProcessor.processBookings();

            System.out.println("\nInventory after concurrent bookings:");
            inventory.displayInventory();

            // ==============================
            // UC12: Persistence Demo
            // ==============================
            System.out.println("\n=== Persistence Demo ===");

            PersistenceService persistenceService = new PersistenceService();

            // Save current state
            persistenceService.saveData(bookingHistory.getAllReservations(), inventory);

            // Load state
            Object[] loadedData = persistenceService.loadData();
            List<Reservation> loadedReservations = (List<Reservation>) loadedData[0];
            RoomInventory loadedInventory = (RoomInventory) loadedData[1];

            System.out.println("\nLoaded Reservations:");
            if (loadedReservations != null) loadedReservations.forEach(System.out::println);
            System.out.println("\nLoaded Inventory:");
            if (loadedInventory != null) loadedInventory.displayInventory();

            // ==============================
            // UC13: Final Integration Demo
            // ==============================
            System.out.println("\n=== Final Integrated Workflow ===");

            // 1. New reservation with add-ons
            Reservation finalReservation = new Reservation("R006", "Suite");
            bookingHistory.addReservation(finalReservation);
            addOnManager.addService(finalReservation.getReservationId(), new AddOnService("Spa", 70));
            addOnManager.addService(finalReservation.getReservationId(), new AddOnService("Dinner", 40));

            // 2. Process concurrency for this new booking
            concurrentProcessor.submitBooking(finalReservation);
            concurrentProcessor.processBookings();

            // 3. Cancel one old reservation to test rollback
            cancellationService.cancelReservation("R002");

            // 4. Save state
            persistenceService.saveData(bookingHistory.getAllReservations(), inventory);

            System.out.println("\nFinal Reservations:");
            bookingHistory.getAllReservations().forEach(System.out::println);
            System.out.println("\nFinal Inventory:");
            inventory.displayInventory();

        } catch (InvalidBookingException e) {
            System.out.println("\n❌ Booking Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n⚠ Unexpected Error: " + e.getMessage());
        }
    }
}