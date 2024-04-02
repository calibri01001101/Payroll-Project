import javax.swing.*;
import java.awt.*;

public class PayrollManager extends JFrame implements Assets {
    private final JTextField employeeName = new JTextField();
    private final JTextField salaryRate = new JTextField();
    private final JTextField daysWorked = new JTextField();
    private final JTextField hoursOvertime = new JTextField();
    private final JTextField hoursLate = new JTextField();
    private final JTextField advancedPay = new JTextField();
    private final JTextField regularHoliday = new JTextField();
    private final JTextField specialHolidays = new JTextField();
    private final JLabel sssLabel = new JLabel();
    private final JLabel tinLabel = new JLabel();
    private final JLabel philHealthLabel = new JLabel();
    private final JLabel pagIbigLabel = new JLabel();

    private double grossPay;
    private double totalGovernmentDeductions;
    private double netPay;
    // Panel for the payroll page that holds all the components
    public JPanel payrollPage() {
        JPanel panel = new JPanel();
        panel.setBackground(SECONDARY_BACKGROUND);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        add(panel);
        panel.add(salaryManagementPanel());
        panel.add(deductionsPanel());
        return panel;
    }
    public JPanel salaryManagementPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(PRIMARY_BACKGROUND);
        panel.setBorder(new RoundedBorder(20, Color.BLACK));
        panel.setBounds(10, 10, 300, 500);
        add(panel);
        title(panel);
        calculateButton(panel);
        textField(panel, 40, "Employee Name", employeeName);
        textField(panel, 90, "Salary Rate", salaryRate);
        textField(panel, 140, "Number of Days Worked", daysWorked);
        textField(panel, 190, "Number of Overtime Hours", hoursOvertime);
        textField(panel, 240, "Number of Hours Late", hoursLate);
        textField(panel, 290, "Advanced Pay (bale)", advancedPay);
        textField(panel, 340, "Number of Regular Holidays", regularHoliday);
        textField(panel, 390, "Number of Special Holidays", specialHolidays);
        return panel;
    }

    public void title(JPanel panel) {
        JLabel label = new JLabel("SALARY MANAGER");
        label.setBounds(10, 10, 290, 20);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(label);
    }

    public void textField(JPanel panel, int y, String title, JTextField value) {
        JLabel label = new JLabel(title);
        label.setBounds(10, y + 20, 250, 30);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(label);

        value.setBounds(10, y, 280, 30);

        panel.add(value);
    }

    public void calculateButton(JPanel panel) {
        JButton button = new JButton("Calculate");
        button.setBounds(190, 460, 100, 20);
        button.setBackground(SECONDARY_BACKGROUND);

        button.addActionListener(e -> {
            double _salaryRate = salaryRate.getText().isEmpty() ? 0 : Double.parseDouble(salaryRate.getText());
            int _daysWorked = daysWorked.getText().isEmpty() ? 0 : Integer.parseInt(daysWorked.getText());
            int _hoursOvertime = hoursOvertime.getText().isEmpty() ? 0 : Integer.parseInt(hoursOvertime.getText());
            int _hoursLate = hoursLate.getText().isEmpty() ? 0 : Integer.parseInt(hoursLate.getText());
            double _advancedPay = advancedPay.getText().isEmpty() ? 0 : Double.parseDouble(advancedPay.getText());
            int _regularHoliday = regularHoliday.getText().isEmpty() ? 0 : Integer.parseInt(regularHoliday.getText());
            int _specialHoliday = specialHolidays.getText().isEmpty() ? 0 : Integer.parseInt(specialHolidays.getText());

            grossPay = grossPayCalculator(_salaryRate, _daysWorked, _hoursOvertime, _regularHoliday, _specialHoliday, _hoursLate, _advancedPay);
            totalGovernmentDeductions = governmentDeductionsCalculator(grossPay);
            netPay = netPayCalculator(grossPay, totalGovernmentDeductions);
        });

        panel.add(button);
    }

    public JPanel deductionsPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(320, 10, 450, 200);
        panel.setBackground(PRIMARY_BACKGROUND);
        panel.setBorder(new RoundedBorder(20, Color.BLACK));
        panel.add(governmentDeductionsLabel());
        panel.setLayout(null);
        deductionAmountLabel(panel, sssLabel, "SSS:", 10, 60);
        deductionAmountLabel(panel, tinLabel, "TIN:", 35, 55);
        deductionAmountLabel(panel, philHealthLabel, "PHIL HEALTH:", 60, 135);
        deductionAmountLabel(panel, pagIbigLabel, "PAG IBIG", 85, 105);
        return panel;
    }

    public JLabel governmentDeductionsLabel() {
        JLabel label = new JLabel("Government Deductions");
        label.setBounds(20, 20, 300, 20);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        return label;
    }

    public void deductionAmountLabel(JPanel panel, JLabel amountLabel, String title, int y, int x) {
        JLabel label = new JLabel(title);
        label.setBounds(20, y, 150, 100);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(label);

        amountLabel.setBounds(x, y, 100, 100);
        amountLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        panel.add(amountLabel);

    }
    // Functions to be used
    public double grossPayCalculator(double salaryRate, int daysWorked, int hoursOvertime, int regularHolidays, int specialHolidays, int hoursLate, double advancedPay) {
        // Additions
        double regularPay = salaryRate * daysWorked;
        double overtimePay = hoursOvertime * ((salaryRate / 8) * 1.1);
        double regularHolidayPay = (salaryRate + (salaryRate * 0.3)) * regularHolidays;
        double specialHolidayPay = salaryRate * specialHolidays;
        // deduction
        double lateDeduction = (salaryRate / 8) * hoursLate;

        return regularPay + overtimePay + regularHolidayPay + specialHolidayPay - lateDeduction - advancedPay;
    }

    public double governmentDeductionsCalculator(double grossPay) {
        double TIN = 0;
        double SSS = grossPay * 0.1;
        double PHIL_HEALTH = 100;
        double PAG_IBIG = 100;
        pagIbigLabel.setText(String.valueOf(TIN));
        sssLabel.setText(String.valueOf(SSS));
        philHealthLabel.setText(String.valueOf(PHIL_HEALTH));
        pagIbigLabel.setText(String.valueOf(PAG_IBIG));
        return TIN + SSS + PHIL_HEALTH + PAG_IBIG;
    }

    public double netPayCalculator(double grossPay, double deductions) {
        return grossPay - deductions;
    }

}