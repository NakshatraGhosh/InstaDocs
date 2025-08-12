package com.InstaDoc.demo.Controllers;


import com.InstaDoc.demo.Models.Appointment;
import com.InstaDoc.demo.Models.AppointmentBookingRequest;
import com.InstaDoc.demo.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


import com.InstaDoc.demo.Models.Appointment;
import com.InstaDoc.demo.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {


    
    @Autowired
    private KafkaTemplate<String, AppointmentBookingRequest> kafkaTemplate;
    @Autowired
    private AppointmentService appointmentService;


    
@PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentBookingRequest request) {
        kafkaTemplate.send("appointment-bookings", request);
        return ResponseEntity.ok("Booking request received. You will be notified soon.");
    }

    // Get all appointments
    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Get appointment by ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    // Get appointments for a specific patient
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    // Get appointments for a specific doctor
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    // Update appointment time
    @PutMapping("/update/{id}")
    public Appointment updateAppointmentTime(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newTime) {
        return appointmentService.updateAppointmentTime(id, newTime);
    }

    // Delete appointment
    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        boolean deleted = appointmentService.deleteAppointment(id);
        return deleted ? "Appointment deleted successfully" : "Appointment not found";
    }}