
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private ArrayList<User> users;

    public LoginFrame() {
        setTitle("Cafe Login");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        users = new ArrayList<>();
        users.add(new User("admin", "admin123", "Admin"));
        users.add(new User("staff", "staff123", "Staff"));

        loginBtn.addActionListener(e -> checkLogin());

        add(userLabel); add(usernameField);
        add(passLabel); add(passwordField);
        add(new JLabel()); add(loginBtn);

        setVisible(true);
    }

    private void checkLogin() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());

        for (User u : users) {
            if (u.getUsername().equals(user) && u.getPassword().equals(pass)) {
                JOptionPane.showMessageDialog(this, "Welcome " + u.getRole() + "!");
                dispose();
                new DashboardFrame(u.getRole());
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Invalid login!");
    }
}
