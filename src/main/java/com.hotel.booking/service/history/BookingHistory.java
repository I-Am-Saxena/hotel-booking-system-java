package com.hotel.booking.service.history;

import com.hotel.booking.model.Reservation;
import java.util.*;

public class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}