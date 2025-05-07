import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField nameField, priceField;
    private JComboBox<String> categoryBox;

    public MenuFrame() {
        setTitle("Menu Management");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"Name", "Category", "Price"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        refreshTable();

        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        nameField = new JTextField();
        priceField = new JTextField();
        categoryBox = new JComboBox<>(new String[]{"Coffee", "Tea", "Snack", "Dessert"});

        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton delBtn = new JButton("Delete");

        addBtn.addActionListener(e -> addMenuItem());
        editBtn.addActionListener(e -> editMenuItem());
        delBtn.addActionListener(e -> deleteMenuItem());

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryBox);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(addBtn);
        inputPanel.add(editBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(delBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (MenuItem item : MenuManager.getItems()) {
            model.addRow(new Object[]{item.getName(), item.getCategory(), item.getPrice()});
        }
    }

    private void addMenuItem() {
        String name = nameField.getText();
        String category = (String) categoryBox.getSelectedItem();
        try {
            double price = Double.parseDouble(priceField.getText());
            MenuManager.addItem(new MenuItem(name, category, price));
            refreshTable();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid price");
        }
    }

    private void editMenuItem() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            String name = nameField.getText();
            String category = (String) categoryBox.getSelectedItem();
            try {
                double price = Double.parseDouble(priceField.getText());
                MenuManager.updateItem(selected, new MenuItem(name, category, price));
                refreshTable();
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid price");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select an item to edit");
        }
    }

    private void deleteMenuItem() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            MenuManager.removeItem(selected);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Select an item to delete");
        }
    }

    private void clearFields() {
        nameField.setText("");
        priceField.setText("");
    }
}
