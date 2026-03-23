package com.hotel.booking.service.persistence;

import com.hotel.booking.inventory.RoomInventory;
import com.hotel.booking.model.Reservation;

import java.io.*;
import java.util.List;

public class PersistenceService {

    private final String fileName = "booking_data.ser";

    public void saveData(List<Reservation> reservations, RoomInventory inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(reservations);
            oos.writeObject(inventory);
            System.out.println("✅ Data saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Failed to save data: " + e.getMessage());
        }
    }

    public Object[] loadData() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("⚠ No persistence file found. Starting fresh.");
            return new Object[]{null, null};
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Reservation> reservations = (List<Reservation>) ois.readObject();
            RoomInventory inventory = (RoomInventory) ois.readObject();
            System.out.println("✅ Data loaded successfully.");
            return new Object[]{reservations, inventory};
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Failed to load data: " + e.getMessage());
            return new Object[]{null, null};
        }
    }
}