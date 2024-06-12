import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PayrollManager extends JFrame implements ActionListener {
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
    JTextArea payslipTextArea = new JTextArea();
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
    // Hashmap for the list of employees
    Map<String, Employee> employeeList = FileFunctions.get();
    private Employee employee;
    // Panel for the payroll page that holds all the components
    public JPanel payrollPage() {
        // Storing the getting all the employees data from the file
        JPanel panel = new JPanel();
        panel.setBackground(Assets.WHITE);
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
        panel.setBackground(Assets.PRIMARY_BACKGROUND);
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
        label.setForeground(Assets.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(label);
    }
    // Text fields for the information needed
    public void textField(JPanel panel, int y, String title, JTextField value) {
        JLabel label = new JLabel(title);
        label.setBounds(10, y + 20, 250, 30);
        label.setForeground(Assets.WHITE);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(label);
        value.setBounds(10, y, 280, 30);
        panel.add(value);
    }
    // Button to do the calculation
    public void calculateButton(JPanel panel) {
        JButton button = new JButton("Calculate");
        button.setBounds(190, 460, 100, 20);
        button.setForeground(Assets.WHITE);
        button.setBackground(Assets.SECONDARY_BACKGROUND);
        // button listener
        button.addActionListener(_ -> {
            if(employeeName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select an employee first.");
                return;
            }
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
                double _basicPay = CalculatorUtils.basicPay(_daysWorked, _salaryRate);
                double _overtimePay = CalculatorUtils.overtime(_hoursOvertime, _salaryRate);
                double _holidayPay = CalculatorUtils.holidayPay(_regularHoliday, _specialHoliday, _salaryRate);
                grossPay = CalculatorUtils.grossPayCalculator(_basicPay, _overtimePay, _holidayPay);
                // Deductions calculation
                double _tinDeduction = CalculatorUtils.tinDeduction();
                double _sssDeduction = CalculatorUtils.sssDeduction(grossPay);
                double _pagIbigDeduction = CalculatorUtils.pagibigDeduction();
                double _philHealthDeduction = CalculatorUtils.philHealthDeduction();
                double _lateDeduction = CalculatorUtils.lateDeduction(_hoursLate, _salaryRate);
                double _baleDeduction = CalculatorUtils.baleDeduction(_advancedPay);
                // Total deductions and net pay calculation
                totalGovernmentDeductions = CalculatorUtils.totalDeductions(_tinDeduction, _pagIbigDeduction, _sssDeduction, _philHealthDeduction, _lateDeduction, _baleDeduction);
                netPay = CalculatorUtils.netPayCalculator(grossPay, totalGovernmentDeductions);
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
                deductionsTotalAmount.setText(numberFormatter(totalGovernmentDeductions));
                netPayAmount.setText(numberFormatter(netPay));
                // Getting the employee details in the hashmap list and
                employee = employeeList.get(employeeName.getText().trim());
                employee.setNetPay(netPay);
                employee.setGrossPay(grossPay);
                employee.setTotalDeductions(totalGovernmentDeductions);

                // Updating the JTextArea with the new payslip information
                updatePayslipTextArea();
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please input a valid number.");
            }
        });
        panel.add(button);
    }

    public JTextArea payslipPanel() {
        payslipTextArea = new JTextArea();
        payslipTextArea.setBounds(320, 10, 450, 500);
        payslipTextArea.setBorder(new RoundedBorder(20, Color.BLACK));
        payslipTextArea.setLayout(null);
        String payslipText = String.format(
                """                        
                        ========================================================
                        MAKTRANS CORPORATION %63s
                        ========================================================
                        Employee name: %s
                        Salary Rate: %s
                        --------------------------------------------------------
                        Earnings
                        --------------------------------------------------------
                        Basic Pay: %s
                        Overtime Pay: %s
                        Holiday Pay: %s
                        --------------------------------------------------------
                        Deductions
                        --------------------------------------------------------
                        TIN Deduction: %s
                        SSS Deduction: %s
                        Pag-ibig Deduction: %s
                        PhilHealth Deduction: %s
                        Late Deduction: %s
                        Bale Deduction: %s
                        --------------------------------------------------------
                        Gross Pay: %s
                        Total Deductions: %s
                        Net Pay: %s
                        ========================================================""",
                dateAndTime(),
                employeeName.getText(),
                salaryRate.getText(),
                basicPay.getText(),
                overtimePay.getText(),
                holidayPay.getText(),
                tinDeduction.getText(),
                sssDeduction.getText(),
                pagibigDeduction.getText(),
                philHealthDeduction.getText(),
                lateDeduction.getText(),
                baleDeduction.getText(),
                grossPayAmount.getText(),
                deductionsTotalAmount.getText(),
                netPayAmount.getText()
        );


        payslipTextArea.setText(payslipText);
        printButton(payslipTextArea);
        return payslipTextArea;
    }

    // Deductions Panel
    public void updatePayslipTextArea() {

        String payslipText = String.format(
                """                        
                        ========================================================
                        MAKTRANS CORPORATION %63s
                        ========================================================
                        Employee name: %s
                        Salary Rate: %s
                        --------------------------------------------------------
                        Earnings
                        --------------------------------------------------------
                        Basic Pay: %s
                        Overtime Pay: %s
                        Holiday Pay: %s
                        --------------------------------------------------------
                        Deductions
                        --------------------------------------------------------
                        TIN Deduction: %s
                        SSS Deduction: %s
                        Pag-ibig Deduction: %s
                        PhilHealth Deduction: %s
                        Late Deduction: %s
                        Bale Deduction: %s
                        --------------------------------------------------------
                        Gross Pay: %s
                        Total Deductions: %s
                        Net Pay: %s
                        ========================================================""",
                dateAndTime(),
                employeeName.getText(),
                salaryRate.getText(),
                basicPay.getText(),
                overtimePay.getText(),
                holidayPay.getText(),
                tinDeduction.getText(),
                sssDeduction.getText(),
                pagibigDeduction.getText(),
                philHealthDeduction.getText(),
                lateDeduction.getText(),
                baleDeduction.getText(),
                grossPayAmount.getText(),
                deductionsTotalAmount.getText(),
                netPayAmount.getText()
        );


        payslipTextArea.setText(payslipText);
        payslipTextArea.setEditable(false);
    }

    // this is for the name combo box
    void namesComboBox(JPanel panel) {
        names = new JComboBox(getAllNames());
        names.setBounds(10, 40, 280, 30);
        names.addActionListener(this);
        panel.add(names);
    }
    // This function displays the current time and date
    String dateAndTime() {
        // Creating an instance of the object DateTimeFormatter and passing an argument for the format of the time and date
        DateTimeFormatter currDate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // Getting the current time and date
        LocalDateTime now = LocalDateTime.now();
        // Displaying the time and date:)
        return currDate.format(now);

    }

    public String[] getAllNames() {
        String[] employeesName = new String[employeeList.size()];
        int index = 0;
        for(Map.Entry<String, Employee> in : employeeList.entrySet()) {
            employeesName[index] = in.getKey();
            index++;
        }
        return employeesName;
    }
    //print button for payslip
    public void printButton(JTextArea panel) {
        JButton button = new JButton("Save & Print");
        button.setBackground(Assets.SECONDARY_BACKGROUND);
        button.setForeground(Assets.WHITE);
        button.setBounds(300, 440, 120, 40);
        button.addActionListener(e -> {
            try {
                panel.print();
                FileFunctions.update(employee.getFullName(), employee);
            }catch(Exception er) {
                System.out.println(er.getMessage());
            }
        });
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
