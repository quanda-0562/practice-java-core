public class Truck extends Vehicle {
    private final double tonnage;

    public Truck(String number, Manufacturer manufacturer, int year, String color, Owner owner, double tonnage) {
        super(number, manufacturer, year, color, owner);
        this.tonnage = requirePositive(tonnage, "Tonnage");
    }

    public double getTonnage() { return tonnage; }
    @Override public String getType() { return "Truck"; }
    @Override protected String getDetails() { return "Tonnage (tons): " + tonnage; }
}
