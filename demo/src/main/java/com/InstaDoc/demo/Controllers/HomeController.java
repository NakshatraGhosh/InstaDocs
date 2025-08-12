package com.InstaDoc.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.InstaDoc.demo.Models.Doctor;
import com.InstaDoc.demo.Repositories.DoctorRepository;
import com.InstaDoc.demo.services.Doctorservice;


@Controller
@RequestMapping("/home")
public class HomeController {
    
    @Autowired
    public final Doctorservice doctorService;
    @Autowired
    public final DoctorRepository doctorRepository;
    public HomeController(Doctorservice doctorService, DoctorRepository doctorRepository) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
    }
   @GetMapping("/first")
    public String index() {
        return "redirect:/home";

    }

     @GetMapping("/findnearbydoctor")
    public String indexa() {
        return "findnearbydoctor";
    }


    @GetMapping("/finddoctors/{latlng}")
public String findDoctorsNearby(@PathVariable String latlng, Model model) {
    String[] parts = latlng.split(",");
    double lat = Double.parseDouble(parts[0]);
    double lng = Double.parseDouble(parts[1]);

    List<Doctor> nearbyDoctors = doctorService.findNearbyDoctors(lat, lng, 50);
    model.addAttribute("doctors", nearbyDoctors);
    return "find-doctors";
}

}
