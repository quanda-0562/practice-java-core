import java.time.LocalDate;

public abstract class Goods {
    private final String code;
    private final String name;
    private final int quantity;
    private final double unitPrice;

    protected Goods(String code, String name, int quantity, double unitPrice) {
        this.code = requireText(code, "Product code");
        this.name = requireText(name, "Product name");
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be >= 0.");
        if (!Double.isFinite(unitPrice) || unitPrice < 0) {
            throw new IllegalArgumentException("Unit price must be finite and >= 0.");
        }
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    protected static String requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " must not be blank.");
        }
        return value.trim();
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public abstract String getType();
    public abstract double getVatRate();
    public abstract String evaluateConsumption(LocalDate today);
    protected abstract String getDetails();

    public double getVatAmount() {
        return quantity * unitPrice * getVatRate();
    }

    public String getInformation(LocalDate today) {
        return String.format(
                "%s | %s | %s | Quantity: %d | Unit price: %.2f | VAT: %.2f | %s | %s",
                getType(), code, name, quantity, unitPrice, getVatAmount(),
                getDetails(), evaluateConsumption(today));
    }
}
