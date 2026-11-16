import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class VehicleManager {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final Map<String, Owner> owners = new LinkedHashMap<>();

    public Owner findOwnerById(String idNumber) { return owners.get(idNumber); }

    public void add(Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "Vehicle");
        if (findByNumber(vehicle.getNumber()) != null) {
            throw new IllegalArgumentException("Duplicate vehicle number.");
        }
        Owner owner = vehicle.getOwner();
        Owner existing = owners.get(owner.getIdNumber());
        if (existing != null && !existing.hasSameInformation(owner)) {
            throw new IllegalArgumentException("ID number already belongs to a different owner profile.");
        }
        owners.putIfAbsent(owner.getIdNumber(), owner);
        vehicles.add(vehicle);
    }

    public Vehicle findByNumber(String number) {
        String normalized = Vehicle.normalizeNumber(number);
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getNumber().equals(normalized)) return vehicle;
        }
        return null;
    }

    public List<Vehicle> findByOwnerId(String idNumber) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getOwner().getIdNumber().equals(idNumber)) result.add(vehicle);
        }
        return result;
    }

    public int deleteByManufacturer(Manufacturer manufacturer) {
        int before = vehicles.size();
        vehicles.removeIf(vehicle -> vehicle.getManufacturer() == manufacturer);
        return before - vehicles.size();
    }

    public Map<Manufacturer, Integer> countByManufacturer() {
        Map<Manufacturer, Integer> counts = new EnumMap<>(Manufacturer.class);
        for (Manufacturer manufacturer : Manufacturer.values()) counts.put(manufacturer, 0);
        for (Vehicle vehicle : vehicles) counts.merge(vehicle.getManufacturer(), 1, Integer::sum);
        return counts;
    }

    public List<Manufacturer> getMostCommonManufacturers() {
        List<Manufacturer> result = new ArrayList<>();
        int maximum = 0;
        for (Map.Entry<Manufacturer, Integer> entry : countByManufacturer().entrySet()) {
            int count = entry.getValue();
            if (count > maximum) {
                maximum = count;
                result.clear();
            }
            if (count > 0 && count == maximum) result.add(entry.getKey());
        }
        return result;
    }

    public List<Vehicle> sortByNumberDescending() {
        vehicles.sort(Comparator.comparing(Vehicle::getNumber).reversed());
        return List.copyOf(vehicles);
    }

    public Map<String, Integer> countByType() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("Car", 0);
        counts.put("Motorcycle", 0);
        counts.put("Truck", 0);
        for (Vehicle vehicle : vehicles) counts.merge(vehicle.getType(), 1, Integer::sum);
        return counts;
    }
}
