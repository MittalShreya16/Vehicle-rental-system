package in.shreya.serviceimpl;

import in.shreya.entity.Rental;
import in.shreya.entity.Vehicle;
import in.shreya.repository.RentalRepository;
import in.shreya.service.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental createRental(Rental rental) {
        Vehicle vehicle = rental.getVehicle();
        LocalDate startDate = rental.getStartDate();
        LocalDate endDate = rental.getEndDate();

        if (startDate.isAfter(endDate)) {
            throw new RuntimeException("End date must be after start date");
        }

        List<Rental> overlappingRentals = rentalRepository.findByVehicleAndStatusAndStartDateLessThanAndEndDateGreaterThan(
                vehicle, "active", endDate, startDate);
        if (!overlappingRentals.isEmpty()) {
            throw new RuntimeException("Vehicle is not available for the selected dates");
        }

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        double totalCost = days * vehicle.getRentalRate();
        rental.setTotalCost(totalCost);
        rental.setStatus("active");
        return rentalRepository.save(rental);
    }

    @Override
    public void returnVehicle(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
        if ("completed".equals(rental.getStatus())) {
            throw new RuntimeException("Rental already completed");
        }
        rental.setStatus("completed");
        rentalRepository.save(rental);
    }

    @Override
    public boolean isVehicleAvailableForPeriod(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        List<Rental> overlappingRentals = rentalRepository.findByVehicleAndStatusAndStartDateLessThanAndEndDateGreaterThan(
                vehicle, "active", endDate, startDate);
        return overlappingRentals.isEmpty();
    }
}
