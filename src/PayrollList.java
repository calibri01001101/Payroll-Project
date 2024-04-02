import javax.swing.*;

public class PayrollList extends JFrame implements Assets {
    public JPanel payrollList() {
        JPanel panel = new JPanel();
        panel.setBackground(SECONDARY_BACKGROUND);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        add(panel);
        return panel;
    }
}
