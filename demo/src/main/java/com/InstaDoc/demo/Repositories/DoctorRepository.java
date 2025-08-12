package com.InstaDoc.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.InstaDoc.demo.Models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // This interface will automatically provide CRUD operations for Doctor entity
    // Additional custom query methods can be defined here if needed
    Doctor findByName(String name);
    Doctor findBySpecialization(String specialization); 
    // You can add more methods as needed for your application
    // For example, to find doctors by latitude and longitude:
     List<Doctor> findByLatitudeAndLongitude(double latitude, double longitude);
    List<Doctor> findByNameContainingIgnoreCase(String name);

}
