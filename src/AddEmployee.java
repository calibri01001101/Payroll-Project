import javax.swing.*;
import java.awt.*;

public class AddEmployee extends JFrame implements Assets {
    private final JTextField fullName = new JTextField();
    private final JTextField phoneNumber = new JTextField();
    private final JTextField address = new JTextField();
    private final JTextField sss = new JTextField();
    private final JTextField tin = new JTextField();
    private final JTextField philHealth = new JTextField();
    private final JTextField pagIbig = new JTextField();

    // Panel for the employee page that holds all the components
    public JPanel addEmployeePage() {
        JPanel panel = new JPanel();
        panel.setBackground(SECONDARY_BACKGROUND);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        add(panel);
        panel.add(personalInformationPanel());
        return panel;
    }
    // Panel for the employee information
    public JPanel personalInformationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(PRIMARY_BACKGROUND);
        panel.setBounds(20, 20, 300, 420);
        personalInformationLabel(panel);
        textField(panel, 40, "Full Name", fullName);
        textField(panel, 90, "Phone Number", phoneNumber);
        textField(panel, 140, "Address", address);
        textField(panel, 190, "SSS", sss);
        textField(panel, 240, "TIN",tin);
        textField(panel, 290, "PAG-IBIG", pagIbig);
        textField(panel, 340, "PHIL-HEALTH", philHealth);
        addButton(panel);
        return panel;
    }
    // Personal Information Text
    public void personalInformationLabel(JPanel panel) {
        JLabel label = new JLabel("Personal Information");
        label.setBounds(10, 10, 200, 20);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(label);
    }

    public void textField(JPanel panel, int y, String title, JTextField value) {
        value.setBounds(10, y, 280, 30);
        panel.add(value);

        JLabel label = new JLabel(title);
        label.setBounds(10, y + 20, 200, 30);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(label);
    }


    public void addButton(JPanel panel) {
        JButton button = new JButton("ADD");
        button.setBounds(190, 390, 100, 20);

        button.addActionListener(e -> {
            System.out.println(fullName.getText());
            System.out.println(phoneNumber.getText());
        });

        panel.add(button);
    }
}
