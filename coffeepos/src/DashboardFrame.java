import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    public DashboardFrame(String role) {
        setTitle("Cafe Dashboard - " + role);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Create a label that welcomes the user based on role
        JLabel label = new JLabel("Welcome to Cafe Management System (" + role + ")");
        add(label);

        // Create the "Manage Menu" button
        JButton menuBtn = new JButton("Manage Menu");
        menuBtn.addActionListener(_ -> new MenuFrame());  // Open MenuFrame on click
        add(menuBtn);  // Add the button to the frame

        // Create the "Process Order" button
        JButton orderBtn = new JButton("Process Order");
        orderBtn.addActionListener(e -> new OrderFrame());  // Open OrderFrame on click
        add(orderBtn);  // Add the button to the frame

        // Add "View Sales Report" button only for admins
        if (role.equals("admin")) {
            JButton reportBtn = new JButton("View Sales Report");
            reportBtn.addActionListener(e -> new ReportFrame());  // Open ReportFrame on click
            add(reportBtn);  // Add the button to the frame
        }

        setVisible(true);  // Make the frame visible
    }

    public static void main(String[] args) {
        // Example usage: Open DashboardFrame with a given role
        new DashboardFrame("admin");
    }
}
