import javax.swing.*;

public class SplashScreen {
    public static void main(String[] args) {
        try {
            // Create a splash screen window
            JWindow splash = new JWindow();
            JLabel img = new JLabel(new ImageIcon("logo.png"));
            splash.getContentPane().add(img);
            splash.setSize(400, 300);
            splash.setLocationRelativeTo(null);
            splash.setVisible(true);

            // Delay for 2 seconds
            Thread.sleep(2000);
            splash.dispose();  // Close the splash screen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
