package in.shreya.service;

import in.shreya.entity.Vehicle;
import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(Long id);
    Vehicle saveVehicle(Vehicle vehicle);
    void deleteVehicle(Long id);
}
