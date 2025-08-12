package com.InstaDoc.demo.services;


import com.InstaDoc.demo.Repositories.AppointmentRepository;
import com.InstaDoc.demo.Models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentCleanupService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Runs every day at 2 AM
    @Scheduled(cron = "0 0 2 * * ?")
    public void removeExpiredAppointments() {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        allAppointments.stream()
            .filter(app -> app.getAppointmentTime() != null && app.getAppointmentTime().isBefore(now))
            .forEach(app -> appointmentRepository.deleteById(app.getId()));
    }
}