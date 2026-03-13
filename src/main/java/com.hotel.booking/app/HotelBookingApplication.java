package com.hotel.booking.app;

import com.hotel.booking.model.*;

public class HotelBookingApplication {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Hotel Booking System ");
        System.out.println(" Application Version : v1.0 ");
        System.out.println("=================================");

        Room singleRoom = new SingleRoom(50);
        Room doubleRoom = new DoubleRoom(80);
        Room suiteRoom = new SuiteRoom(150);

        System.out.println("\nAvailable Rooms:");
        System.out.println(singleRoom.getRoomDetails());
        System.out.println(doubleRoom.getRoomDetails());
        System.out.println(suiteRoom.getRoomDetails());
    }
}