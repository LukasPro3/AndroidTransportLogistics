package com.example.lab3.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public class Driver extends User implements Serializable {
    private String medicalSertificate;
    private String driversLicense;

    private Truck assignedTruckId;

    private List<Trip> trips;

    private List<Cargo> cargoList;

    public Driver(String firstName, String lastName, String phoneNumber, String email, LocalDate birthDate, String login, String password, UserRole driver, boolean isAdmin) {
        super(firstName, lastName, phoneNumber, email, birthDate, login, password,UserRole.DRIVER, isAdmin);
    }

    public Driver(String medicalSertificate, String driversLicense, Truck assignedTruckId, List<Trip> trips, List<Cargo> cargoList) {
        this.medicalSertificate = medicalSertificate;
        this.driversLicense = driversLicense;
        this.assignedTruckId = assignedTruckId;
        this.trips = trips;
        this.cargoList = cargoList;
    }

    public Driver() {
    }

}
