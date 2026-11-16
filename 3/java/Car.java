public class Car extends Vehicle {
    private final int seats;
    private final String engineType;

    public Car(String number, Manufacturer manufacturer, int year, String color, Owner owner,
               int seats, String engineType) {
        super(number, manufacturer, year, color, owner);
        if (seats <= 0) throw new IllegalArgumentException("Seats must be > 0.");
        this.seats = seats;
        this.engineType = Owner.requireText(engineType, "Engine type");
    }

    public int getSeats() { return seats; }
    public String getEngineType() { return engineType; }
    @Override public String getType() { return "Car"; }
    @Override protected String getDetails() { return "Seats: " + seats + " | Engine: " + engineType; }
}
