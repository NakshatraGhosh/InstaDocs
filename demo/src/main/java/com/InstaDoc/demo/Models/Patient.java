package com.InstaDoc.demo.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Patient {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int age;
    private String gender;

    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;


    public Patient() {
    }

    public Patient(Long id, String name, int age, String gender, double latitude, double longitude, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.latitude = latitude;
        this.longitude = longitude;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    // getters and setters
}

