import java.time.LocalDateTime;
import java.util.ArrayList;

public class SalesManager {
    public static class Sale {
        public ArrayList<OrderItem> items;
        public double total;
        public LocalDateTime time;

        public Sale(ArrayList<OrderItem> items, double total, LocalDateTime time) {
            this.items = items;
            this.total = total;
            this.time = time;
        }
    }

    private static ArrayList<Sale> salesList = new ArrayList<>();

    public static void recordSale(ArrayList<OrderItem> orderItems, double total) {
        ArrayList<OrderItem> copy = new ArrayList<>(orderItems);
        salesList.add(new Sale(copy, total, LocalDateTime.now()));
    }

    public static ArrayList<Sale> getSales() {
        return salesList;
    }

    public static double getTotalRevenue() {
        return salesList.stream().mapToDouble(s -> s.total).sum();
    }
}
// Compare this snippet from Main.java:
// public class Main {