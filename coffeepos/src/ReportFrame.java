import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ReportFrame extends JFrame {
    public ReportFrame() {
        setTitle("Sales Report");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"Time", "Items", "Total"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (SalesManager.Sale sale : SalesManager.getSales()) {
            StringBuilder items = new StringBuilder();
            for (OrderItem item : sale.items) {
                items.append(item.getItem().getName()).append(" x").append(item.getQuantity()).append(", ");
            }

            model.addRow(new Object[]{
                sale.time.format(formatter),
                items.toString(),
                sale.total
            });
        }

        JLabel totalLabel = new JLabel("Total Revenue: $" + SalesManager.getTotalRevenue());
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
//         String category = (String) categoryBox.getSelectedItem();
//         double price = Double.parseDouble(priceField.getText());