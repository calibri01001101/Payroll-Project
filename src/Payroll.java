import javax.swing.*;
import java.awt.*;

public class Payroll extends JFrame implements Assets {
    // Panel for the payroll page that holds all the components
    public JPanel payrollPage() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
        panel.setBounds(0, 0, 800, 600);
        add(panel);
        return panel;
    }

}
