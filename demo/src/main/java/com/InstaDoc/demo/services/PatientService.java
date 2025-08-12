package com.InstaDoc.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.InstaDoc.demo.Models.Patient;

@Service
public class PatientService {

    private Map<Long, Patient> patientMap = new HashMap<>();
    private long currentId = 1;

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientMap.values());
    }

    public Patient getPatientById(Long id) {
        return patientMap.get(id);
    }

    public Patient createPatient(Patient patient) {
        patient.setId(currentId++);
        patientMap.put(patient.getId(), patient);
        return patient;
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = patientMap.get(id);
        if (existingPatient != null) {
            updatedPatient.setId(id);
            patientMap.put(id, updatedPatient);
            return updatedPatient;
        }
        return null;
    }

    public boolean deletePatient(Long id) {
        return patientMap.remove(id) != null;
    }

}
