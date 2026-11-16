public class Motorcycle extends Vehicle {
    private final double capacity;

    public Motorcycle(String number, Manufacturer manufacturer, int year, String color, Owner owner, double capacity) {
        super(number, manufacturer, year, color, owner);
        this.capacity = requirePositive(capacity, "Capacity");
    }

    public double getCapacity() { return capacity; }
    @Override public String getType() { return "Motorcycle"; }
    @Override protected String getDetails() { return "Capacity (cc): " + capacity; }
}
