package com.hotel.booking.service.validation;

public class BookingValidator {

    public static void validateRoomType(String roomType) {
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }
    }

    public static void validateInventory(int count) {
        if (count < 0) {
            throw new InvalidBookingException("Inventory cannot be negative");
        }
    }
}