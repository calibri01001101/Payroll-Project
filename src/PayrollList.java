import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class PayrollList extends JFrame implements Assets {
    public JPanel payrollList() {
        JPanel panel = new JPanel();
        panel.setBackground(WHITE);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        add(panel);
        panel.add(payrollTable());
        return panel;
    }

    // Table for the payroll information
    public JPanel payrollTable() {
        JPanel panel = new JPanel();
        panel.setBounds(20, 20, 750, 520 );
        panel.setBackground(PRIMARY_BACKGROUND);
        panel.setLayout(new BorderLayout());

        String[][] data = employeesList();
        Object[] columns = {"Full Name", "Position", "Gross Pay", "Deductions", "Net pay"};
        DefaultTableModel model = new DefaultTableModel(data, columns);

        JTable table = new JTable(model);
        table.setRowHeight(20);
        table.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        customizeRow(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        employeesList();

        return panel;

    }
    // Function to get the employees from a file
    public String[][] employeesList() {
        // get all the employees
        Map<String, Employee> employeesHashMap = FileFunctions.get();
        // store into a 2d array
        String[][] employeesArray = new String[employeesHashMap.size()][];
        int index = 0;
        for(Map.Entry<String, Employee> employee : employeesHashMap.entrySet()) {
            employeesArray[index] = new String[]{
                    employee.getValue().getFullName(),
                    employee.getValue().getPosition(),
                    String.valueOf(employee.getValue().getGrossPay()),
                    String.valueOf(employee.getValue().getTotalDeductions()),
                    String.valueOf(employee.getValue().getNetPay())
            };
            index++;
        }

        return employeesArray;
    }

    private void customizeRow(JTable table) {
        TableColumn column = table.getColumnModel().getColumn(4);
        column.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) value;
                if (!status.equalsIgnoreCase(String.valueOf(0.0))) {
                    component.setBackground(Color.GREEN);
                    component.setForeground(Color.WHITE);
                } else {
                    component.setBackground(Color.RED);
                    component.setForeground(Color.WHITE);
                }
                return component;
            }
        });
    }
}
