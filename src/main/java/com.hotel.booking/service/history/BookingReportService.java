package com.hotel.booking.service.history;

import com.hotel.booking.model.Reservation;

public class BookingReportService {

    public void printReport(BookingHistory history) {
        System.out.println("\n=== Booking History Report ===");

        for (Reservation r : history.getAllReservations()) {
            System.out.println(r);
        }
    }
}