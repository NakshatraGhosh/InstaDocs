package com.InstaDoc.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.InstaDoc.demo.Models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // This interface will automatically provide CRUD operations for Patient entity
    // Additional custom query methods can be defined here if needed

    // You can add more methods as needed for your application
    // For example, to find patients by name:
    List<Patient> findByNameContainingIgnoreCase(String name);

    List<Patient> findByAge(int age);
    List<Patient> findByGender(String gender);
    List<Patient> findByLatitudeAndLongitude(double latitude, double longitude);
    

}
