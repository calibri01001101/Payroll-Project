import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Dashboard extends JFrame implements Assets{
    // Panel for the dashboard page that holds all the components
    // This dashboard page contains the summary of all the amounts and all the employee's details,
    public JPanel dashboardPage() {
        // This panel is the panel for the whole dashboard page
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setBackground(WHITE);
        dashboardPanel.setBounds(0, 0, 800, 600);
        dashboardPanel.setLayout(null);
        // To add this panel on the main container which is the main frame
        add(dashboardPanel);
        // To add a summary box for the total of employees
        dashboardPanel.add(summaryBox(590, "Total Employees", String.valueOf(FileFunctions.get().size())));
        // This is the table in the container
        dashboardPanel.add(tablePanel());
        // This is just the h1 in the dashboard page
        companyNameLabel(dashboardPanel);
        return dashboardPanel;
    }

    public void companyNameLabel(JPanel panel) {
        JLabel label = new JLabel("MAKTRANS CORPORATION");
        label.setBounds(20, 10, 500, 100);
        label.setForeground(PRIMARY_BACKGROUND);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        JLabel label2 = new JLabel("Payroll System");
        label2.setBounds(20, 40, 500, 100);
        label2.setForeground(PRIMARY_BACKGROUND);
        label2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(label2);
        panel.add(label);
    }

    public JPanel summaryBox(int x, String title, String amount) {
        // Panel that holds the Heading and subheading or the amount of money and the text above it
        JPanel panel = new JPanel();
        panel.setBackground(PRIMARY_BACKGROUND);
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
        subHeading.setForeground(WHITE);
        panel.add(subHeading);
        return panel;
    }

    public JPanel tablePanel() {
        JPanel panel = new JPanel();
        panel.setBounds(20, 140, 750, 400 );
        panel.setBackground(PRIMARY_BACKGROUND);
        panel.setLayout(new BorderLayout());

        String[][] data = employeesData();
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
    public String[][] employeesData() {
        Map<String, Employee> employeesHashMap = FileFunctions.get();
        List<String[]> employeesList = new ArrayList<>();
        for(Map.Entry<String, Employee> employee : employeesHashMap.entrySet()) {
            employeesList.add(employee.getValue().toArray());
        }
        return employeesList.toArray(new String[0][]);
    }

    // this is the code for the color of the rows
    private void setCustomRowColors(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? WHITE : SECONDARY_BACKGROUND);
                return c;
            }
        });
    }
}
