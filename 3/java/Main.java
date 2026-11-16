import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();
        try {
            while (true) {
                System.out.println("\n1. Add vehicle\n2. Search by vehicle number\n3. Search by owner ID"
                        + "\n4. Delete by manufacturer\n5. Manufacturers with most vehicles"
                        + "\n6. Sort by vehicle number descending\n7. Statistics by vehicle type\n0. Exit");
                try {
                    switch (readInt("Choose: ")) {
                        case 1:
                            manager.add(readVehicle(manager));
                            System.out.println("Added successfully.");
                            break;
                        case 2:
                            Vehicle found = manager.findByNumber(readText("Vehicle number: "));
                            System.out.println(found == null ? "No vehicle found." : found);
                            break;
                        case 3: display(manager.findByOwnerId(readText("Owner ID: "))); break;
                        case 4:
                            System.out.println("Deleted vehicles: " + manager.deleteByManufacturer(readManufacturer()));
                            break;
                        case 5:
                            List<Manufacturer> top = manager.getMostCommonManufacturers();
                            if (top.isEmpty()) System.out.println("No vehicles managed.");
                            else {
                                Map<Manufacturer, Integer> counts = manager.countByManufacturer();
                                for (Manufacturer manufacturer : top) {
                                    System.out.println(manufacturer + ": " + counts.get(manufacturer));
                                }
                            }
                            break;
                        case 6: display(manager.sortByNumberDescending()); break;
                        case 7:
                            manager.countByType().forEach((type, count) -> System.out.println(type + ": " + count));
                            break;
                        case 0: return;
                        default: System.out.println("Invalid choice.");
                    }
                } catch (IllegalArgumentException exception) {
                    System.out.println("Error: " + exception.getMessage());
                }
            }
        } catch (NoSuchElementException exception) {
            System.out.println("\nInput closed.");
        }
    }

    private static Vehicle readVehicle(VehicleManager manager) {
        System.out.println("1. Car\n2. Motorcycle\n3. Truck");
        int type = readInt("Vehicle type: ");
        if (type < 1 || type > 3) throw new IllegalArgumentException("Invalid vehicle type.");
        String number = readText("Vehicle number (5 characters): ");
        Manufacturer manufacturer = readManufacturer();
        int year = readInt("Manufacture year: ");
        String color = readText("Color: ");
        String id = readText("Owner ID (12 digits): ");
        Owner owner = manager.findOwnerById(id);
        if (owner == null) owner = new Owner(id, readText("Full name: "), readText("Email: "));
        else System.out.println("Using existing owner: " + owner);
        switch (type) {
            case 1: return new Car(number, manufacturer, year, color, owner,
                    readInt("Number of seats: "), readText("Engine type: "));
            case 2: return new Motorcycle(number, manufacturer, year, color, owner, readDouble("Capacity (cc): "));
            default: return new Truck(number, manufacturer, year, color, owner, readDouble("Tonnage (tons): "));
        }
    }

    private static Manufacturer readManufacturer() {
        Manufacturer[] manufacturers = Manufacturer.values();
        for (int i = 0; i < manufacturers.length; i++) System.out.println((i + 1) + ". " + manufacturers[i]);
        int choice = readInt("Manufacturer: ");
        if (choice < 1 || choice > manufacturers.length) throw new IllegalArgumentException("Invalid manufacturer.");
        return manufacturers[choice - 1];
    }

    private static void display(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) System.out.println("No vehicles found.");
        for (Vehicle vehicle : vehicles) System.out.println(vehicle);
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return INPUT.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readText(prompt)); }
            catch (NumberFormatException exception) { System.out.println("Enter a valid integer."); }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                double value = Double.parseDouble(readText(prompt));
                if (Double.isFinite(value)) return value;
            } catch (NumberFormatException exception) {
                // Ask again for invalid numeric input.
            }
            System.out.println("Enter a finite number (use a decimal point).");
        }
    }
}
