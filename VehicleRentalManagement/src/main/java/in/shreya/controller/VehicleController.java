package in.shreya.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.shreya.entity.Vehicle;
import in.shreya.service.RentalService;
import in.shreya.service.VehicleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private RentalService rentalService;

    @GetMapping({"", "/", "/list"})
    public String listVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        LocalDate currentDate = LocalDate.now();
        List<Map<String, Object>> vehicleList = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            boolean available = rentalService.isVehicleAvailableForPeriod(vehicle, currentDate, currentDate);
            Map<String, Object> vehicleMap = new HashMap<>();
            vehicleMap.put("vehicle", vehicle);
            vehicleMap.put("available", available);
            vehicleList.add(vehicleMap);
        }
        model.addAttribute("vehicleList", vehicleList);
        return "vehicleList";
    }

    @GetMapping("/new")
    public String showVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicleForm";
    }

    @PostMapping("/save")
    public String saveVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/edit/{id}")
    public String editVehicle(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        return "vehicleForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }
}
