import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class DSHH {
    private Goods[] goods = new Goods[10];
    private int size;

    public int getSize() { return size; }

    public boolean add(Goods item) {
        Objects.requireNonNull(item, "Goods");
        for (int i = 0; i < size; i++) {
            if (goods[i].getCode().equals(item.getCode())) return false;
        }
        if (size == goods.length) goods = Arrays.copyOf(goods, goods.length * 2);
        goods[size++] = item;
        return true;
    }

    public void displayAll(LocalDate today) {
        if (size == 0) System.out.println("Inventory is empty.");
        for (int i = 0; i < size; i++) {
            System.out.println(goods[i].getInformation(today));
        }
    }

    public long getTotalQuantity(String type) {
        long total = 0;
        for (int i = 0; i < size; i++) {
            if (goods[i].getType().equals(type)) total += goods[i].getQuantity();
        }
        return total;
    }

    public double getTotalVat(String type) {
        double total = 0;
        for (int i = 0; i < size; i++) {
            if (goods[i].getType().equals(type)) total += goods[i].getVatAmount();
        }
        return total;
    }

    public void displaySummary() {
        for (String type : new String[]{"Food", "Electronics", "Crockery"}) {
            System.out.printf("%s | Total quantity: %d | Total VAT: %.2f%n",
                    type, getTotalQuantity(type), getTotalVat(type));
        }
    }
}
