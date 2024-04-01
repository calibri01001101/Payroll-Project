import javax.swing.*;

public class AddEmployee extends JFrame implements Assets {
    // Panel for the employee page that holds all the components
    public JPanel addEmployeePage() {
        JPanel panel = new JPanel();
        panel.setBackground(SECONDARY_BACKGROUND);
        panel.setBounds(0, 0, 800, 600);
        add(panel);
        return panel;
    }
}
