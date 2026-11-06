import java.time.LocalDate;

public class Electronics extends Goods {
    private final int warrantyMonths;
    private final double capacityKw;

    public Electronics(String code, String name, int quantity, double unitPrice,
                       int warrantyMonths, double capacityKw) {
        super(code, name, quantity, unitPrice);
        if (warrantyMonths < 0) throw new IllegalArgumentException("Warranty months must be >= 0.");
        if (!Double.isFinite(capacityKw) || capacityKw < 0) {
            throw new IllegalArgumentException("Capacity must be finite and >= 0.");
        }
        this.warrantyMonths = warrantyMonths;
        this.capacityKw = capacityKw;
    }

    public int getWarrantyMonths() { return warrantyMonths; }
    public double getCapacityKw() { return capacityKw; }
    @Override public String getType() { return "Electronics"; }
    @Override public double getVatRate() { return 0.10; }

    @Override
    public String evaluateConsumption(LocalDate today) {
        return getQuantity() < 3 ? "Selling well" : "Not evaluated";
    }

    @Override
    protected String getDetails() {
        return "Warranty months: " + warrantyMonths + " | Capacity (kW): " + capacityKw;
    }
}
