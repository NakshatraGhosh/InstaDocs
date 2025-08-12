package com.InstaDoc.demo.services;



import com.InstaDoc.demo.Models.Appointment;
import com.InstaDoc.demo.Models.AppointmentNotification;
import com.InstaDoc.demo.Models.Doctor;
import com.InstaDoc.demo.Models.Patient;
import com.InstaDoc.demo.Repositories.AppointmentRepository;
import com.InstaDoc.demo.Repositories.DoctorRepository;
import com.InstaDoc.demo.Repositories.PatientRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public Appointment createAppointment(Long patientId, Long doctorId, LocalDateTime appointmentTime) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);

        if (patientOpt.isPresent() && doctorOpt.isPresent()) {
            // Check for double booking for the doctor at the same time
            boolean isBooked = appointmentRepository.findAll().stream()
                .anyMatch(a -> a.getDoctor().getId().equals(doctorId)
                        && a.getAppointmentTime().equals(appointmentTime));
            if (isBooked) {
                // Doctor already has an appointment at this time
                return null;
            }
            Appointment appointment = new Appointment();
            appointment.setPatient(patientOpt.get());
            appointment.setDoctor(doctorOpt.get());
            appointment.setAppointmentTime(appointmentTime);


            Appointment savedAppointment = appointmentRepository.save(appointment);

            return savedAppointment;
        }
        return null;
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointment by ID
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    // Get appointments for a specific patient
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        return patientOpt.map(patient -> 
            appointmentRepository.findAll().stream()
                .filter(a -> a.getPatient().getId().equals(patientId))
                .toList()
        ).orElse(List.of());
    }

    // Get appointments for a specific doctor
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        return doctorOpt.map(doctor -> 
            appointmentRepository.findAll().stream()
                .filter(a -> a.getDoctor().getId().equals(doctorId))
                .toList()
        ).orElse(List.of());
    }

    // Update appointment time
    public Appointment updateAppointmentTime(Long appointmentId, LocalDateTime newTime) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setAppointmentTime(newTime);
            return appointmentRepository.save(appointment);
        }
        return null;
    }

    // Delete appointment
    public boolean deleteAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }}