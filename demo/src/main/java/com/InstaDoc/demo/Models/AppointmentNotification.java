package com.InstaDoc.demo.Models;



public class AppointmentNotification {
    private Long doctorId;
    private String message;

    public AppointmentNotification() {}

    public AppointmentNotification(Long doctorId, String message) {
        this.doctorId = doctorId;
        this.message = message;
    }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
