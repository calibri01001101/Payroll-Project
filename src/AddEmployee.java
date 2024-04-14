import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class AddEmployee extends JFrame implements Assets {
    private final JTextField fullName = new JTextField();
    private final JTextField phoneNumber = new JTextField();
    private final JTextField position = new JTextField();
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
        panel.setBorder(new RoundedBorder(20, Color.BLACK));
        personalInformationLabel(panel);
        textField(panel, 40, "Full Name", fullName);
        textField(panel, 90, "Phone Number", phoneNumber);
        textField(panel, 140, "Position", position);
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
    // Text field and label
    public void textField(JPanel panel, int y, String title, JTextField value) {
        JLabel label = new JLabel(title);
        label.setBounds(10, y + 20, 200, 30);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(label);
        value.setBounds(10, y, 280, 30);
        panel.add(value);
    }
    // Button for adding an employee
    public void addButton(JPanel panel) {
        JButton button = new JButton("ADD");
        button.setBounds(190, 390, 100, 20);
        button.setBackground(SECONDARY_BACKGROUND);
        button.addActionListener(e -> {
            try {
                // Object for writing a data to a file
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees_details", true));
                // Setting the value of the variable by getting the value of the text field
                String full_name = fullName.getText().trim();
                String phone_number = phoneNumber.getText().trim();
                String _address = position.getText().trim();
                // I used ternary operator here so if the value of the text field is empty it will show unknown
                String sssNumber = sss.getText().trim().isEmpty() ? "unknown" : sss.getText().trim();
                String tinNumber = tin.getText().trim().isEmpty() ? "unknown" : tin.getText().trim();
                String philHealthNumber = philHealth.getText().trim().isEmpty() ? "unknown" : philHealth.getText().trim();
                String pagIbigNumber = pagIbig.getText().trim().trim().isEmpty() ? "unknown" : pagIbig.getText().trim();
                // Checking if the value of full name, phone number and address is set.
                if(!full_name.isEmpty() && !phone_number.isEmpty() && !_address.isEmpty()) {
                    writer.write(full_name + " | " +
                            phone_number + " | " +
                            _address + " | " +
                            sssNumber + " | " +
                            tinNumber + " | " +
                            philHealthNumber + " | " +
                            pagIbigNumber + " | " +
                            "0" + " | " +
                            "0" + " | " +
                            "0" + "\n"
                    );
                    writer.close();
                    JOptionPane.showMessageDialog(addEmployeePage(), "Added successfully.");
                    return;
                }
                // If important information is empty this will show
                JOptionPane.showMessageDialog(addEmployeePage(), "Full Name, Phone Number and Position can't be empty.");
                writer.close();
            } catch (InputMismatchException | IOException el) {
                JOptionPane.showMessageDialog(addEmployeePage(), "Invalid input. Please try again.");
            }
        });
        panel.add(button);
    }

    public void emptyTextField() {
        fullName.setText("");
        phoneNumber.setText("");
        position.setText("");
        sss.setText("");
        tin.setText("");
        philHealth.setText("");
        pagIbig.setText("");

    }

}