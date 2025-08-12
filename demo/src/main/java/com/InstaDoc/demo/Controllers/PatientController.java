package com.InstaDoc.demo.Controllers;

import com.InstaDoc.demo.Models.Patient;
import com.InstaDoc.demo.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/patients")
public class PatientController {

    @GetMapping("/page")
    public String getMethodName() {
       return "Patientform";
    }
    

    @Autowired
    private PatientService patientService;

    @GetMapping("/add")
    public String showPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "Patientform";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient, Model model) {
        patientService.createPatient(patient);
        model.addAttribute("message", "Patient added successfully!");
        return "redirect:/patients/add";
    }


    @GetMapping("/list")
    public String listPatients(Model model) {   
        model.addAttribute("patients", patientService.getAllPatients());
        return "patient-list";
    }
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id, Model model) {
        patientService.deletePatient(id);
        model.addAttribute("message", "Patient deleted successfully!");
        return "redirect:/patients/list";
    }
}