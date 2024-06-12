import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class ManageEmployees extends JFrame implements ActionListener {
    private final JTextField lastName = new JTextField();
    private final JTextField firstName = new JTextField();
    private final JTextField middleInitial = new JTextField();
    private final JTextField phoneNumber = new JTextField();
    private final JTextField position = new JTextField();
    private final JTextField sss = new JTextField();
    private final JTextField tin = new JTextField();
    private final JTextField philHealth = new JTextField();
    private final JTextField pagIbig = new JTextField();
    private final JTextField editPhoneNumber = new JTextField();
    private final JTextField editPosition = new JTextField();
    private final JTextField editTIN = new JTextField();
    private final JTextField editPAGIBIG = new JTextField();
    private final JTextField editPHILHEALTH = new JTextField();
    private final JTextField editSSS = new JTextField();
    private String employeeFullName;
    JPanel personalInformationPanel;
    JPanel addEmployeePanel;
    HashMap<String, Employee> employeeList = FileFunctions.get();
    private static JComboBox names;
    private Employee currentEmployee;

    // Panel for the employee page that holds all the components
    public JPanel addEmployeePage() {
        addEmployeePanel = new JPanel();
        addEmployeePanel.setBackground(Assets.WHITE);
        addEmployeePanel.setBounds(0, 0, 800, 600);
        addEmployeePanel.setLayout(null);
        add(addEmployeePanel);
        editEmployeePanel(addEmployeePanel);
        addEmployeePanel.add(personalInformationPanel());
        return addEmployeePanel;
    }
    // Panel for the employee information
    public JPanel personalInformationPanel() {
        personalInformationPanel = new JPanel();
        personalInformationPanel.setLayout(null);
        personalInformationPanel.setBackground(Assets.PRIMARY_BACKGROUND);
        personalInformationPanel.setBounds(20, 20, 300, 520);
        personalInformationPanel.setBorder(new RoundedBorder(20, Color.BLACK));
        panelInformationLabel(personalInformationPanel, "Add Employee");
        textField(personalInformationPanel, 40, "Last Name", lastName);
        textField(personalInformationPanel, 90, "First Name", firstName);
        textField(personalInformationPanel, 140, "Middle Initial", middleInitial);
        textField(personalInformationPanel, 190, "Phone Number", phoneNumber);
        textField(personalInformationPanel, 240, "Position", position);
        textField(personalInformationPanel, 290, "SSS", sss);
        textField(personalInformationPanel, 340, "TIN",tin);
        textField(personalInformationPanel, 390, "PAG-IBIG", pagIbig);
        textField(personalInformationPanel, 440, "PHIL-HEALTH", philHealth);
        addButton(personalInformationPanel);
        return personalInformationPanel;
    }
    // Personal Information Text
    public void panelInformationLabel(JPanel panel, String title) {
        JLabel label = new JLabel(title);
        label.setBounds(10, 10, 200, 20);
        label.setForeground(Assets.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(label);
    }
    // Text field and label
    public void textField(JPanel panel, int y, String title, JTextField value) {
        JLabel label = new JLabel(title);
        label.setBounds(10, y + 20, 200, 30);
        label.setForeground(Assets.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(label);
        value.setBounds(10, y, 280, 30);
        panel.add(value);
    }
    // Button for adding an employee
    public void addButton(JPanel panel) {
        JButton button = new JButton("ADD");
        button.setBounds(190, 490, 100, 20);
        button.setBackground(Assets.SECONDARY_BACKGROUND);
        button.setForeground(Assets.WHITE);
        button.addActionListener(_ -> addNewEmployee());
        panel.add(button);
    }
    // Function to empty text fields after adding an employee
    public void emptyTextField() {
        lastName.setText("");
        firstName.setText("");
        phoneNumber.setText("");
        position.setText("");
        sss.setText("");
        tin.setText("");
        philHealth.setText("");
        pagIbig.setText("");

    }

    // Edit Employee panel
    public void editEmployeePanel(JPanel panel) {
        JPanel editEmployeePanel = new JPanel();
        editEmployeePanel.setLayout(null);
        editEmployeePanel.setBackground(Assets.PRIMARY_BACKGROUND);
        editEmployeePanel.setBounds(330, 20, 440, 520);
        editEmployeePanel.setBorder(new RoundedBorder(20, Color.BLACK));
        panel.add(editEmployeePanel);

        panelInformationLabel(editEmployeePanel, "Employee Look-up");
        employeesNameComboBox(editEmployeePanel);
        selectButton(editEmployeePanel);
        removeEmployeeButton(editEmployeePanel);
        modifyEmployeeInfoButton(editEmployeePanel);

        textField(editEmployeePanel, 90, "Phone Number", editPhoneNumber);
        textField(editEmployeePanel, 140, "Position", editPosition);
        textField(editEmployeePanel, 190, "SSS", editSSS);
        textField(editEmployeePanel, 240, "TIN", editTIN);
        textField(editEmployeePanel, 290, "PHIL-HEALTH", editPHILHEALTH);
        textField(editEmployeePanel, 340, "PAG-IBIG", editPAGIBIG);
    }

    void selectButton(JPanel panel) {
        JButton selectButton = new JButton("Select");
        selectButton.setForeground(Assets.WHITE);
        selectButton.setBackground(Assets.SECONDARY_BACKGROUND);
        selectButton.setBounds(300, 50, 80, 30);
        selectButton.addActionListener(_ -> {
            employeeLookUp();
            }

        );
        panel.add(selectButton);
    }
    void removeEmployeeButton(JPanel panel) {
        JButton removeEmployee = new JButton("Remove");
        removeEmployee.setForeground(Color.WHITE);
        removeEmployee.setBackground(Color.RED);
        removeEmployee.setBounds(210, 460, 100, 40);
        removeEmployee.addActionListener(_ -> {
            if(employeeFullName == null) {
                JOptionPane.showMessageDialog(this, "Please select an employee first.");
                return;
            }

            FileFunctions.delete(employeeFullName);
            JOptionPane.showMessageDialog(this, "Deleted Successfully.");
            emptyField();
        });

        panel.add(removeEmployee);
    }

    void modifyEmployeeInfoButton(JPanel panel) {
        JButton modifyDetails = new JButton("Modify");
        modifyDetails.setBackground(Assets.SECONDARY_BACKGROUND);
        modifyDetails.setForeground(Assets.WHITE);
        modifyDetails.setBounds(320, 460, 100, 40);
        modifyDetails.addActionListener(_ -> {
            if(employeeFullName == null) {
                JOptionPane.showMessageDialog(this, "Please select an employee first.");
                return;
            }
            modifyAndSaveChanges();
            emptyField();
            JOptionPane.showMessageDialog(this, "Updated Successfully");

        });
        panel.add(modifyDetails);
    }

    public void emptyField() {
        phoneNumber.setText("");
        position.setText("");
        sss.setText("");
        tin.setText("");
        philHealth.setText("");
        pagIbig.setText("");
        employeeFullName = null;

    }



    public void employeesNameComboBox(JPanel panel) {
        names = new JComboBox(getEmployeesName());
        names.setBounds(10, 50, 280, 30);
        names.addActionListener(this);
        panel.add(names);

    }

    public String[] getEmployeesName(){
        Map<String, Employee> employeesHashMap = FileFunctions.get();
        String[] employees = new String[employeesHashMap.size()];
        int index = 0;
        for(Map.Entry<String, Employee> employee : employeesHashMap.entrySet()) {
            employees[index] =  employee.getKey();
            index++;
        }
        return employees;
    }

    public void modifyAndSaveChanges() {
        try {
            currentEmployee.setPhoneNumber(editPhoneNumber.getText().trim());
            currentEmployee.setPosition(editPosition.getText().trim());
            currentEmployee.setSss(editSSS.getText().trim());
            currentEmployee.setTin(editTIN.getText().trim());
            currentEmployee.setPhilHeath(editPHILHEALTH.getText().trim());
            currentEmployee.setPagIbig(editPAGIBIG.getText().trim());
            FileFunctions.update(employeeFullName, employeeList.get(employeeFullName));
        }catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid position");
        }


    }
    // function for adding a new employee
    public void addNewEmployee() {
        try {
            // Setting the value of the variable by getting the value of the text field
            String last_name = lastName.getText().trim();
            String first_name = firstName.getText().trim();
            String middle_initial = middleInitial.getText().trim();
            String fullName = first_name.concat(STR." \{middle_initial}").concat(STR." \{last_name}");
            String phone_number = phoneNumber.getText().trim();
            String _position = position.getText().trim();
            // I used ternary operator here so if the value of the text field is empty it will show unknown
            String sssNumber = sss.getText().trim().isEmpty() ? "unknown" : sss.getText().trim();
            String tinNumber = tin.getText().trim().isEmpty() ? "unknown" : tin.getText().trim();
            String philHealthNumber = philHealth.getText().trim().isEmpty() ? "unknown" : philHealth.getText().trim();
            String pagIbigNumber = pagIbig.getText().trim().trim().isEmpty() ? "unknown" : pagIbig.getText().trim();
            // Checking if the value of full name, phone number and position is set.
            if(!last_name.isEmpty() && !phone_number.isEmpty() && !_position.isEmpty()) {
                Employee employee = new Employee(fullName, phone_number, _position, sssNumber, tinNumber, philHealthNumber, pagIbigNumber, 0, 0 ,0);
                FileFunctions.create("employees_details", employee);
                emptyTextField();
                JOptionPane.showMessageDialog(this, "Added successfully.");
                return;
            }

            // If important information is empty this will show
            JOptionPane.showMessageDialog(this, "Full Name, Phone Number and Position can't be empty.");

        } catch (InputMismatchException e) {
            JOptionPane.showMessageDialog(addEmployeePage(), "Invalid input. Please try again.");
        }
    }

    public void employeeLookUp() {
        currentEmployee = employeeList.get(employeeFullName);
        editPhoneNumber.setText(currentEmployee.getPhoneNumber());
        editPosition.setText(currentEmployee.getPosition());
        editSSS.setText(currentEmployee.getSss());
        editTIN.setText(currentEmployee.getTin());
        editPHILHEALTH.setText(currentEmployee.getPhilHeath());
        editPAGIBIG.setText(currentEmployee.getPagIbig());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == names)
            employeeFullName = (String) names.getSelectedItem();
    }
}