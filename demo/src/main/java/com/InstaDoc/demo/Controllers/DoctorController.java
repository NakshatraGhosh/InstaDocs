package com.InstaDoc.demo.Controllers;

import com.InstaDoc.demo.Models.Doctor;
import com.InstaDoc.demo.services.Doctorservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {


  

    @Autowired
    private Doctorservice doctorService;

     @GetMapping("/page")
    public String getMethodName() {
       return "Doctorform";
    }

    @GetMapping("/add")
    public String showDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "Doctorform";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor, Model model) {
        doctorService.createDoctor(doctor);
        model.addAttribute("message", "Doctor added successfully!");
        return "redirect:/doctors/add";
    }

    @GetMapping("/list")
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctor-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id, Model model) {
        doctorService.deleteDoctor(id);
        model.addAttribute("message", "Doctor deleted successfully!");
        return "redirect:/doctors/list";
    }

    @GetMapping("/edit/{id}")
    public String editDoctor(@PathVariable Long id, Model model) {  
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            model.addAttribute("doctor", doctor);
            return "Doctorform";
        } else {
            model.addAttribute("message", "Doctor not found!");
            return "redirect:/doctors/list";
        }
    }

    @GetMapping("/nearby")
    public String findNearbyDoctors(@RequestParam double lat, @RequestParam double lng, Model model) {
        double radiusKm = 50.0;
        model.addAttribute("doctors", doctorService.findNearbyDoctors(lat, lng, radiusKm));
        return "doctor-list";
    }

}