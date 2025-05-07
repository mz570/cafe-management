import javax.swing.*;
import java.awt.*;

public class UIStyle {
    public static void applyTheme(Component comp) {
        comp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        if (comp instanceof JPanel || comp instanceof JFrame) {
            comp.setBackground(new Color(0xf5f0e6));
        }
    }

    public static void styleButton(JButton btn) {
        btn.setBackground(new Color(0x6f4e37));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
