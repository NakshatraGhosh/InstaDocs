package com.InstaDoc.demo.Repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.InstaDoc.demo.Models.Appointment;
import com.InstaDoc.demo.Models.Doctor;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Additional query methods can be defined here if needed
// In AppointmentRepository
Optional<Appointment> findByDoctorIdAndAppointmentTime(Long doctorId, LocalDateTime appointmentTime);

boolean existsByDoctorAndAppointmentTime(Doctor doctor, LocalDateTime appointmentTime);
}
