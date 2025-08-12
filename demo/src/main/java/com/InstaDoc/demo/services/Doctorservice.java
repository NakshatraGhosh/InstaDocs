package com.InstaDoc.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InstaDoc.demo.Models.Doctor;
import com.InstaDoc.demo.Repositories.DoctorRepository;

@Service
public class Doctorservice {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findNearbyDoctors(double userLat, double userLng, double radiusKm) {
        List<Doctor> allDoctors = doctorRepository.findAll();

        return allDoctors.stream()
            .filter(doc -> distanceInKm(userLat, userLng, doc.getLatitude(), doc.getLongitude()) <= radiusKm)
            .collect(Collectors.toList());
    }

    // Haversine formula to calculate distance between two points
    private double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of earth in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> getDoctorsByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name);
    }

public List<Doctor> getDoctorsByLocation(double latitude, double longitude) {
    return doctorRepository.findByLatitudeAndLongitude(latitude, longitude);
}

}
