import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class PayrollManager extends JFrame implements Assets, Calculators, ActionListener, FileMethods {
    private final JTextField employeeName = new JTextField();
    private final JTextField salaryRate = new JTextField("560");
    private final JTextField daysWorked = new JTextField();
    private final JTextField hoursOvertime = new JTextField();
    private final JTextField hoursLate = new JTextField();
    private final JTextField advancedPay = new JTextField();
    private final JTextField regularHoliday = new JTextField();
    private final JTextField specialHolidays = new JTextField();
    private final JLabel fullNameLabel = new JLabel();
    private final JLabel basicPay = new JLabel();
    private final JLabel overtimePay = new JLabel();
    private final JLabel holidayPay = new JLabel();
    private final JLabel tinDeduction = new JLabel();
    private final JLabel sssDeduction = new JLabel();
    private final JLabel pagibigDeduction = new JLabel();
    private final JLabel philHealthDeduction = new JLabel();
    private final JLabel lateDeduction = new JLabel();
    private final JLabel baleDeduction = new JLabel();
    private final JLabel grossPayAmount = new JLabel();
    private final JLabel deductionsTotalAmount = new JLabel();
    private final JLabel netPayAmount = new JLabel();
    private double grossPay;
    private double totalGovernmentDeductions;
    private double netPay;
    private static JComboBox names;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
    // Hashmap for the list of employees
    HashMap<String, Employee> employeeList = new HashMap<>();
    // Panel for the payroll page that holds all the components
    public JPanel payrollPage() {
        // Storing the getting all the employees data from the file
        FileMethods.getData(employeeList);
        JPanel panel = new JPanel();
        panel.setBackground(WHITE);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        add(panel);
        panel.add(salaryManagementPanel());
        panel.add(payslipPanel());
        return panel;
    }
    // Panel for the salary manager
    public JPanel salaryManagementPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(PRIMARY_BACKGROUND);
        panel.setBorder(new RoundedBorder(20, Color.BLACK));
        panel.setBounds(10, 10, 300, 500);
        add(panel);
        title(panel);
        calculateButton(panel);
        namesComboBox(panel);
        textField(panel, 90, "Salary Rate", salaryRate);
        textField(panel, 140, "Number of Days Worked", daysWorked);
        textField(panel, 190, "Number of Overtime Hours", hoursOvertime);
        textField(panel, 240, "Number of Hours Late", hoursLate);
        textField(panel, 290, "Advanced Pay (bale)", advancedPay);
        textField(panel, 340, "Number of Regular Holidays", regularHoliday);
        textField(panel, 390, "Number of Special Holidays", specialHolidays);
        return panel;
    }
    // Just the text or h1 for salary manager panel
    public void title(JPanel panel) {
        JLabel label = new JLabel("SALARY MANAGER");
        label.setBounds(10, 10, 290, 20);
        label.setForeground(WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(label);
    }
    // Text fields for the information needed
    public void textField(JPanel panel, int y, String title, JTextField value) {
        JLabel label = new JLabel(title);
        label.setBounds(10, y + 20, 250, 30);
        label.setForeground(WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(label);
        value.setBounds(10, y, 280, 30);
        panel.add(value);
    }
    // Button to do the calculation
    public void calculateButton(JPanel panel) {
        JButton button = new JButton("Calculate");
        button.setBounds(190, 460, 100, 20);
        button.setForeground(WHITE);
        button.setBackground(SECONDARY_BACKGROUND);
        // button listener
        button.addActionListener(e -> {
            try {
                // I used ternary operator for less line of codes
                // Example
                // salaryRate.getText().isEmpty() ? 0 : Double.parseDouble(salaryRate.getText());
                // if the text is empty, set the value to zero else set the value by getting the text from text field and converting it to a double
                double _salaryRate = salaryRate.getText().isEmpty() ? 0 : Double.parseDouble(salaryRate.getText());
                int _daysWorked = daysWorked.getText().isEmpty() ? 0 : Integer.parseInt(daysWorked.getText());
                int _hoursOvertime = hoursOvertime.getText().isEmpty() ? 0 : Integer.parseInt(hoursOvertime.getText());
                int _hoursLate = hoursLate.getText().isEmpty() ? 0 : Integer.parseInt(hoursLate.getText());
                double _advancedPay = advancedPay.getText().isEmpty() ? 0 : Double.parseDouble(advancedPay.getText());
                int _regularHoliday = regularHoliday.getText().isEmpty() ? 0 : Integer.parseInt(regularHoliday.getText());
                int _specialHoliday = specialHolidays.getText().isEmpty() ? 0 : Integer.parseInt(specialHolidays.getText());
                // Earnings calculation
                double _basicPay = Calculators.basicPay(_daysWorked, _salaryRate);
                double _overtimePay = Calculators.overtime(_hoursOvertime, _salaryRate);
                double _holidayPay = Calculators.holidayPay(_regularHoliday, _specialHoliday, _salaryRate);
                grossPay = Calculators.grossPayCalculator(_basicPay, _overtimePay, _holidayPay);
                // Deductions calculation
                double _tinDeduction = Calculators.tinDeduction();
                double _sssDeduction = Calculators.sssDeduction(grossPay);
                double _pagIbigDeduction = Calculators.pagibigDeduction();
                double _philHealthDeduction = Calculators.philHealthDeduction();
                double _lateDeduction = Calculators.lateDeduction(_hoursLate, _salaryRate);
                double _baleDeduction = Calculators.baleDeduction(_advancedPay);
                // Total deductions and net pay calculation
                totalGovernmentDeductions = Calculators.totalDeductions(_tinDeduction, _pagIbigDeduction, _sssDeduction, _philHealthDeduction, _lateDeduction, _baleDeduction);
                netPay = Calculators.netPayCalculator(grossPay, totalGovernmentDeductions);
                // Setting the text for each label in the panel
                fullNameLabel.setText(employeeName.getText());
                basicPay.setText(numberFormatter(_basicPay));
                overtimePay.setText(numberFormatter(_overtimePay));
                holidayPay.setText(numberFormatter(_holidayPay));
                tinDeduction.setText(numberFormatter(_tinDeduction));
                sssDeduction.setText(numberFormatter(_sssDeduction));
                pagibigDeduction.setText(numberFormatter(_pagIbigDeduction));
                philHealthDeduction.setText(numberFormatter(_philHealthDeduction));
                lateDeduction.setText(numberFormatter(_lateDeduction));
                baleDeduction.setText(numberFormatter(_baleDeduction));
                grossPayAmount.setText(numberFormatter(grossPay));
                deductionsTotalAmount.setText(numberFormatter(grossPay));
                netPayAmount.setText(numberFormatter(netPay));
                // Getting the employee details in the hashmap list and
                Employee employee = employeeList.get(employeeName.getText().trim());
                employee.setNetPay(netPay);
                employee.setGrossPay(grossPay);
                employee.setTotalDeductions(totalGovernmentDeductions);
                // Updating the details on the database/file
                FileMethods.updateData(employeeList);
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please input a valid number.");
            }
        });
        panel.add(button);
    }
    // Deductions Panel
    public JPanel payslipPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(320, 10, 450, 500);
        panel.setBackground(PRIMARY_BACKGROUND);
        panel.setBorder(new RoundedBorder(20, Color.BLACK));
        panel.add(companyNameLabel());
        panel.setLayout(null);
        aboutMoney(panel, "EARNINGS", 70);
        titleAndAmountLabel(panel, fullNameLabel, "Name: ", 10, 20, 70);
        titleAndAmountLabel(panel, basicPay, "Basic: ", 75, 70, 120);
        titleAndAmountLabel(panel, overtimePay, "Overtime: ", 95, 70, 150);
        titleAndAmountLabel(panel, holidayPay, "Holiday: ", 115, 70, 140);
        titleAndAmountLabel(panel, grossPayAmount, "Gross Pay: ", 225, 70, 155);
        aboutMoney(panel, "DEDUCTIONS", 250);
        titleAndAmountLabel(panel, tinDeduction, "TIN: ", 75, 250, 285);
        titleAndAmountLabel(panel, pagibigDeduction, "PAG-IBIG: ", 95, 250, 330);
        titleAndAmountLabel(panel, sssDeduction, "SSS: ", 115, 250, 290);
        titleAndAmountLabel(panel, philHealthDeduction, "PHIL-HEALTH: ", 135, 250, 360);
        titleAndAmountLabel(panel, lateDeduction, "Late: ", 155, 250, 290);
        titleAndAmountLabel(panel, baleDeduction, "Bale: ", 175, 250, 290);
        titleAndAmountLabel(panel, deductionsTotalAmount, "Deductions: ", 225, 250, 345);
        titleAndAmountLabel(panel, netPayAmount, "Net Pay: ", 265, 250, 320);
        dateAndTime(panel);
        printButton(panel);
        return panel;
    }
    // h1 for government deductions panel
    public JLabel companyNameLabel() {
        JLabel label = new JLabel("EXL CORPORATION");
        label.setBounds(20, 25, 300, 20);
        label.setForeground(WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        return label;
    }
    // Texts to show the deductions information
    public void titleAndAmountLabel(JPanel panel, JLabel amountLabel, String title, int y, int x, int x1) {
        JLabel label = new JLabel(title);
        label.setBounds(x, y, 150, 100);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(label);
        label.setForeground(WHITE);
        amountLabel.setBounds(x1, y, 300, 100);
        amountLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        amountLabel.setForeground(WHITE);
        panel.add(amountLabel);
    }
    // EARNING/DEDUCTIONS LABEL
    public void aboutMoney(JPanel panel, String title, int x) {
        JLabel label = new JLabel(title);
        label.setBounds(x, 85, 300, 15);
        label.setForeground(WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(label);
    }
    // this is for the name combo box
    void namesComboBox(JPanel panel) {
        names = new JComboBox(getAllNames());
        names.setBounds(10, 40, 280, 30);
        names.addActionListener(this);
        panel.add(names);
    }
    // This function displays the current time and date
    void dateAndTime(JPanel panel) {
        // Creating an instance of the object DateTimeFormatter and passing an argument for the format of the time and date
        DateTimeFormatter currDate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // Getting the current time and date
        LocalDateTime now = LocalDateTime.now();
        // Displaying the time and date:)
        JLabel label = new JLabel(currDate.format(now));
        label.setBounds(340, 10, 200, 10);
        label.setForeground(WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        panel.add(label);
    }
    // Function to get all the names from the employee list
    public String[] getAllNames() {
        // Array for all the names
        String[] employeesName = new String[employeeList.size()];
        // Setting the initial index to 1
        int index = 0;
        // For each employee I will get the key from it and store in the array
        for(Map.Entry<String, Employee> in : employeeList.entrySet()) {
            employeesName[index] = in.getKey();
            // increasing the index by one each loop
            index++;
        }
        return employeesName;
    }
    //print button for payslip
    public void printButton(JPanel panel) {
        JButton button = new JButton("Print");
        button.setBackground(SECONDARY_BACKGROUND);
        button.setForeground(WHITE);
        button.setBounds(350, 440, 80, 40);
        panel.add(button);
    }


    public String numberFormatter(Double amount) {
        return decimalFormat.format(amount);
    }
    // Event listener for the combo box
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==names)
            employeeName.setText((String) names.getSelectedItem());
    }
}
