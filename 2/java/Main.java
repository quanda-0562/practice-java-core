import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        DSHH inventory = new DSHH();
        try {
            while (true) {
                System.out.println("\n1. Add goods\n2. Display goods and consumption\n3. Inventory and VAT summary\n0. Exit");
                switch (readInt("Choose: ")) {
                    case 1:
                        try {
                            Goods item = readGoods();
                            if (item != null) {
                                System.out.println(inventory.add(item)
                                        ? "Added successfully." : "Duplicate product code. Goods were not added.");
                            }
                        } catch (IllegalArgumentException exception) {
                            System.out.println("Cannot add goods: " + exception.getMessage());
                        }
                        break;
                    case 2: inventory.displayAll(LocalDate.now()); break;
                    case 3: inventory.displaySummary(); break;
                    case 0: return;
                    default: System.out.println("Invalid choice.");
                }
            }
        } catch (NoSuchElementException exception) {
            System.out.println("\nInput closed.");
        }
    }

    private static Goods readGoods() {
        System.out.println("1. Food\n2. Electronics\n3. Crockery");
        int type = readInt("Goods type: ");
        if (type < 1 || type > 3) {
            System.out.println("Invalid goods type.");
            return null;
        }
        String code = readText("Product code: ");
        String name = readText("Product name: ");
        int quantity = readInt("Inventory quantity (>= 0): ");
        double price = readDouble("Unit price (>= 0): ");
        switch (type) {
            case 1:
                return new Food(code, name, quantity, price,
                        readDate("Manufacture date"), readDate("Expiration date"), readText("Supplier: "));
            case 2:
                return new Electronics(code, name, quantity, price,
                        readInt("Warranty months (>= 0): "), readDouble("Capacity kW (>= 0): "));
            default:
                return new Crockery(code, name, quantity, price,
                        readText("Manufacturer: "), readDate("Arrival date"));
        }
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

    private static LocalDate readDate(String prompt) {
        while (true) {
            try { return LocalDate.parse(readText(prompt + " (yyyy-MM-dd): ")); }
            catch (DateTimeParseException exception) { System.out.println("Enter a valid date, e.g. 2026-11-03."); }
        }
    }
}
