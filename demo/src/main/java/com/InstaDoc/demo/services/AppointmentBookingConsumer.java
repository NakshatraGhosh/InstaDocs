package com.InstaDoc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.InstaDoc.demo.Repositories.DoctorRepository;
import com.InstaDoc.demo.Repositories.PatientRepository;
import com.InstaDoc.demo.Repositories.AppointmentRepository;
import com.InstaDoc.demo.Models.Appointment;
import com.InstaDoc.demo.Models.AppointmentBookingRequest;
import com.InstaDoc.demo.Models.Doctor;
import com.InstaDoc.demo.Models.Patient;







@Service
public class AppointmentBookingConsumer {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @KafkaListener(topics = "appointment-bookings", groupId = "booking-group", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void processBooking(AppointmentBookingRequest request) {
        System.out.println("Processing booking: " + request);

        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElseThrow();
        Patient patient = patientRepository.findById(request.getPatientId()).orElseThrow();

        boolean isBooked = appointmentRepository.existsByDoctorAndAppointmentTime(doctor, request.getAppointmentTime());
        if (isBooked) {
            System.out.println("Double booking detected");
            return;
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointmentRepository.save(appointment);

        System.out.println("Booking saved successfully");
    }
}
