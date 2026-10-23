package in.shreya.repository;

import in.shreya.entity.Rental;
import in.shreya.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByVehicleAndStatusAndStartDateLessThanAndEndDateGreaterThan(
            Vehicle vehicle, String status, LocalDate endDate, LocalDate startDate);
}
