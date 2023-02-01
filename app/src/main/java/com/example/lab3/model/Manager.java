package com.example.lab3.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public class Manager extends User implements Serializable {

    private List<Cargo> processedCargoList;

    public Manager(String firstName, String lastName, String phoneNumber, String email, LocalDate birthDate, String login, String password, boolean isAdmin) {
        super(firstName, lastName, phoneNumber, email, birthDate, login, password,UserRole.MANAGER, isAdmin);
    }

    public Manager() {

    }
}
