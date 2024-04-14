import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard extends JFrame implements Assets, FileMethods{
    private int totalEmployees;
    private double totalDeductions;
    private double totalGrossPay;
    private double totalNetPay;
    // List for the employees detail
    private final HashMap<String, Employee> employeesDetail = new HashMap<>();
    // Panel for the dashboard page that holds all the components
    // This dashboard page contains the summary of all the amounts and all the employee's details,
    public JPanel dashboardPage() {
        divInformation();
        JPanel panel = new JPanel();
        panel.setBackground(SECONDARY_BACKGROUND);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        add(panel);
        panel.add(amountBox(20, "Total Employees", String.valueOf(totalEmployees)));
        panel.add(amountBox(210, "Total Net Pay", String.valueOf(totalNetPay)));
        panel.add(amountBox(400, "Total Gross Pay", String.valueOf(totalGrossPay)));
        panel.add(amountBox(590, "Total Deductions", String.valueOf(totalDeductions)));
        panel.add(tablePanel());
        return panel;
    }

    public JPanel amountBox(int x, String title, String amount) {
        // Panel that holds the Heading and subheading or the amount of money and the text above it
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(x, 20, 180, 100);
        Border roundedBorder = new RoundedBorder(20, PRIMARY_BACKGROUND);
        panel.setBorder(roundedBorder);
        panel.setLayout(null);
        add(panel);
        // Text above the amount label
        JLabel heading = new JLabel(title);
        heading.setForeground(SUBHEADING_COLOR);
        heading.setBounds(10, 10, 180, heading.getPreferredSize().height);
        panel.add(heading);
        // The amount of money label
        JLabel subHeading = new JLabel(amount);
        subHeading.setForeground(SECONDARY_BACKGROUND);
        subHeading.setFont(new Font(subHeading.getFont().getName(), Font.BOLD, 25));
        subHeading.setBounds(10, 30, 180, 25);
        panel.add(subHeading);

        return panel;
    }


    public JPanel tablePanel() {
        JPanel panel = new JPanel();
        panel.setBounds(20, 140, 750, 400 );
        panel.setBackground(PRIMARY_BACKGROUND);
        panel.setLayout(new BorderLayout());

        String[][] data = getAllData("employees_details");
        Object[] columns = {"Full Name", "Phone Number", "Position"};
        DefaultTableModel model = new DefaultTableModel(data, columns);

        JTable table = new JTable(model);
        table.setRowHeight(20);
        setCustomRowColors(table);
        table.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;

    }
    // Function to get all the information from the text file and returning 2D array
    public String[][] getAllData(String fileLocation) {
        // List of data from the file
        List<String[]> dataList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line;
            while ((line = reader.readLine()) != null) {
                // Creating an array of data using the function .split()
                String[] data = line.split("\\|");
                // Adding the data on the list
                dataList.add(data);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // converting the array list to 2D array and returning it
        return dataList.toArray(new String[0][]);
    }
    // This function fill out the information for the four boxes
    public void divInformation() {
        // Getting all the data first from the file
        FileMethods.getData(employeesDetail);
        // Getting the size of the array and initialized the value of total employees
        totalEmployees = employeesDetail.size();
        // Looping for each data in the hash map
        for(Map.Entry<String, Employee> employee : employeesDetail.entrySet()) {
            // Getting the deduction from an employee and adding it on total deductions
            totalDeductions += employee.getValue().getTotalDeductions();
            // Getting the net pay from an employee and adding it on total net pay
            totalNetPay += employee.getValue().getNetPay();
            // Getting the gross pay from an employee and adding it on total gross pay
            totalGrossPay += employee.getValue().getGrossPay();
        }
    }
    private void setCustomRowColors(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? PRIMARY_BACKGROUND : SECONDARY_BACKGROUND);
                return c;
            }
        });
    }
}
