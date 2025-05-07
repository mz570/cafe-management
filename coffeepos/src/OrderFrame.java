import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OrderFrame extends JFrame {
    private JComboBox<String> itemBox;
    private JTextField quantityField;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private ArrayList<OrderItem> orderList = new ArrayList<>();

    public OrderFrame() {
        setTitle("Order Processing");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        itemBox = new JComboBox<>();
        quantityField = new JTextField();

        for (MenuItem item : MenuManager.getItems()) {
            itemBox.addItem(item.getName());
        }

        JButton addBtn = new JButton("Add to Order");
        addBtn.addActionListener(e -> addToOrder());

        topPanel.add(new JLabel("Item:"));
        topPanel.add(itemBox);
        topPanel.add(new JLabel("Quantity:"));
        topPanel.add(quantityField);
        topPanel.add(new JLabel(""));
        topPanel.add(addBtn);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Item", "Qty", "Unit Price", "Total"}, 0);
        orderTable = new JTable(tableModel);
        add(new JScrollPane(orderTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("Total: 0.0");
        JButton billBtn = new JButton("Generate Bill");

        billBtn.addActionListener(e -> generateBill());

        bottomPanel.add(totalLabel);
        bottomPanel.add(billBtn);

        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void addToOrder() {
        String itemName = (String) itemBox.getSelectedItem();
        int qty;

        try {
            qty = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid quantity");
            return;
        }

        MenuItem selected = null;
        for (MenuItem item : MenuManager.getItems()) {
            if (item.getName().equals(itemName)) {
                selected = item;
                break;
            }
        }

        if (selected != null && qty > 0) {
            OrderItem orderItem = new OrderItem(selected, qty);
            orderList.add(orderItem);
            tableModel.addRow(new Object[]{
                selected.getName(), qty, selected.getPrice(), orderItem.getTotal()
            });
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid selection or quantity");
        }
    }

    private void updateTotal() {
        double total = 0;
        for (OrderItem item : orderList) {
            total += item.getTotal();
        }
        totalLabel.setText("Total: " + total);
    }

    private void generateBill() {
        StringBuilder receipt = new StringBuilder("Cafe Receipt:\n");
        receipt.append("------------------------\n");

        double total = 0;
        for (OrderItem item : orderList) {
            receipt.append(item.getItem().getName())
                .append(" x").append(item.getQuantity())
                .append(" = ").append(item.getTotal()).append("\n");
            total += item.getTotal();
        }

        receipt.append("------------------------\nTotal: ").append(total);
        JOptionPane.showMessageDialog(this, new JTextArea(receipt.toString()), "Receipt", JOptionPane.INFORMATION_MESSAGE);

        // Record the sale
        SalesManager.recordSale(orderList, total);

        // Clear the order
        tableModel.setRowCount(0);
        orderList.clear();
        totalLabel.setText("Total: 0.0");
    }
}
