import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Crockery extends Goods {
    private final String manufacturer;
    private final LocalDate arrivalDate;

    public Crockery(String code, String name, int quantity, double unitPrice,
                    String manufacturer, LocalDate arrivalDate) {
        super(code, name, quantity, unitPrice);
        this.manufacturer = requireText(manufacturer, "Manufacturer");
        this.arrivalDate = Objects.requireNonNull(arrivalDate, "Arrival date");
    }

    public String getManufacturer() { return manufacturer; }
    public LocalDate getArrivalDate() { return arrivalDate; }
    @Override public String getType() { return "Crockery"; }
    @Override public double getVatRate() { return 0.10; }

    @Override
    public String evaluateConsumption(LocalDate today) {
        return getQuantity() > 50 && ChronoUnit.DAYS.between(arrivalDate, today) > 10
                ? "Slow sale" : "Not evaluated";
    }

    @Override
    protected String getDetails() {
        return "Manufacturer: " + manufacturer + " | Arrived: " + arrivalDate;
    }
}
