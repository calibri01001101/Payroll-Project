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
        customizeRow(table);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);
        employeesList();

        return panel;

    }
    // Function to get the employees from a file
    public String[][] employeesList() {
        // Creating an array list of employees
        ArrayList<Employee> employees  = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("employees_details"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                // Putting an object of employee inside the arraylist
                employees.add(new Employee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9])));
            }
        }catch (FileNotFoundException e) {
            e.getLocalizedMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Converting the arraylist to a 2D array
        String[][] employeeArray = new String[employees.size()][];
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            employeeArray[i] = new String[]{emp.getFullName(), emp.getPosition(), String.valueOf(emp.getGrossPay()), String.valueOf(emp.getTotalDeductions()), String.valueOf(emp.getNetPay())};
        }
        return employeeArray;
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
