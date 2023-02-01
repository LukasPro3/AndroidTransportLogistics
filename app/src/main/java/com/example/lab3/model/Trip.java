package com.example.lab3.model;


import java.io.Serializable;
import java.time.LocalTime;


public class Trip implements Serializable {
    private int id;
    private int cargoId;                // koki krovini veza
    private String startPoint;
    private String destination;
    private LocalTime stopTime;       // kada sustojo
    private String stopLocation;      // kur sustojo

    private User driversId;   //kuris veza

    public Trip(int cargoId, String startPoint, String destination, LocalTime stopTime, String stopLocation, User driversId) {
        this.cargoId = cargoId;
        this.startPoint = startPoint;
        this.destination = destination;
        this.stopTime = stopTime;
        this.stopLocation = stopLocation;
        this.driversId = driversId;
    }

    public Trip() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalTime stopTime) {
        this.stopTime = stopTime;
    }

    public String getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
    }

    public User getDriversId() {
        return driversId;
    }

    public void setDriversId(User driversId) {
        this.driversId = driversId;
    }

    @Override
    public String toString() {
        return id + destination;
    }
}
