import java.time.LocalDate;
import java.util.Objects;

public class Food extends Goods {
    private final LocalDate manufactureDate;
    private final LocalDate expirationDate;
    private final String supplier;

    public Food(String code, String name, int quantity, double unitPrice,
                LocalDate manufactureDate, LocalDate expirationDate, String supplier) {
        super(code, name, quantity, unitPrice);
        this.manufactureDate = Objects.requireNonNull(manufactureDate, "Manufacture date");
        this.expirationDate = Objects.requireNonNull(expirationDate, "Expiration date");
        if (expirationDate.isBefore(manufactureDate)) {
            throw new IllegalArgumentException("Expiration date must be on or after manufacture date.");
        }
        this.supplier = requireText(supplier, "Supplier");
    }

    public LocalDate getManufactureDate() { return manufactureDate; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public String getSupplier() { return supplier; }
    @Override public String getType() { return "Food"; }
    @Override public double getVatRate() { return 0.05; }

    @Override
    public String evaluateConsumption(LocalDate today) {
        return getQuantity() > 0 && expirationDate.isBefore(today)
                ? "Hard to sell" : "Not evaluated";
    }

    @Override
    protected String getDetails() {
        return "Manufactured: " + manufactureDate + " | Expires: " + expirationDate
                + " | Supplier: " + supplier;
    }
}
