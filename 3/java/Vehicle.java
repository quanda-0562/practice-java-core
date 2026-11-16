import java.time.Year;
import java.util.Locale;
import java.util.Objects;

public abstract class Vehicle {
    private final String number;
    private final Manufacturer manufacturer;
    private final int manufactureYear;
    private final String color;
    private final Owner owner;

    protected Vehicle(String number, Manufacturer manufacturer, int manufactureYear, String color, Owner owner) {
        this.number = normalizeNumber(number);
        if (this.number.length() != 5) {
            throw new IllegalArgumentException("Vehicle number must have exactly 5 characters.");
        }
        this.manufacturer = Objects.requireNonNull(manufacturer, "Manufacturer");
        if (manufactureYear <= 2000 || manufactureYear > Year.now().getValue()) {
            throw new IllegalArgumentException("Manufacture year must be > 2000 and <= the current year.");
        }
        this.manufactureYear = manufactureYear;
        this.color = Owner.requireText(color, "Color");
        this.owner = Objects.requireNonNull(owner, "Owner");
    }

    public static String normalizeNumber(String number) {
        return Owner.requireText(number, "Vehicle number").toUpperCase(Locale.ROOT);
    }

    protected static double requirePositive(double value, String field) {
        if (!Double.isFinite(value) || value <= 0) {
            throw new IllegalArgumentException(field + " must be finite and > 0.");
        }
        return value;
    }

    public String getNumber() { return number; }
    public Manufacturer getManufacturer() { return manufacturer; }
    public int getManufactureYear() { return manufactureYear; }
    public String getColor() { return color; }
    public Owner getOwner() { return owner; }
    public abstract String getType();
    protected abstract String getDetails();

    @Override public String toString() {
        return getType() + " | Number: " + number + " | " + manufacturer + " | Year: " + manufactureYear
                + " | Color: " + color + " | " + getDetails() + " | Owner: " + owner;
    }
}
