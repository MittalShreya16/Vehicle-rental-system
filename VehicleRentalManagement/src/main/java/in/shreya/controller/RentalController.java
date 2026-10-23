package in.shreya.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.shreya.entity.Customer;
import in.shreya.entity.Rental;
import in.shreya.entity.Vehicle;
import in.shreya.service.CustomerService;
import in.shreya.service.RentalService;
import in.shreya.service.VehicleService;

import java.time.LocalDate;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @GetMapping({"", "/", "/list"})
    public String listRentals(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        return "rentalList";
    }

    @GetMapping("/new")
    public String showRentalForm(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "rentalForm";
    }

    @PostMapping("/create")
    public String createRental(
            @RequestParam Long customer_id,
            @RequestParam Long vehicle_id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {
        Customer customer = customerService.getCustomerById(customer_id);
        Vehicle vehicle = vehicleService.getVehicleById(vehicle_id);
        if (customer == null || vehicle == null) {
            model.addAttribute("error", "Invalid customer or vehicle");
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "rentalForm";
        }
        Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setVehicle(vehicle);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        try {
            rentalService.createRental(rental);
            return "redirect:/rentals";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "rentalForm";
        }
    }

    @GetMapping("/return/{id}")
    public String returnVehicle(@PathVariable Long id) {
        rentalService.returnVehicle(id);
        return "redirect:/rentals";
    }
}
