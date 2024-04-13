import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class Dashboard extends JFrame implements Assets{
    // Panel for the dashboard page that holds all the components
    // This dashboard page contains the summary of all the amounts and all the employee's details,
    public JPanel dashboardPage() {
        JPanel panel = new JPanel();
        panel.setBackground(SECONDARY_BACKGROUND);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        add(panel);
        panel.add(amountBox(20, "AMOUNT", "0"));
        panel.add(amountBox(210, "AMOUNT", "0"));
        panel.add(amountBox(400, "AMOUNT", "0"));
        panel.add(amountBox(590, "AMOUNT", "0"));

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

        String[][] data = Assets.fileReader("employees_details");
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

    // Method to customize the appearance of a specific row
    // I used chatGpt for this :)
    private void customizeRow(JTable table) {
        TableColumn column = table.getColumnModel().getColumn(2);
        column.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) value;
                if ("Paid".equals(status)) {
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
