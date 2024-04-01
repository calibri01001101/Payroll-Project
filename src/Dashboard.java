import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Dashboard extends JFrame implements Assets{
    // Panel for the dashboard page that holds all the components
    public JPanel dashboardPage() {
        JPanel panel = new JPanel();
        panel.setBackground(SECONDARY_BACKGROUND);
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        add(panel);
        panel.add(amountBox(20, "AMOUNT", "$1,342.92"));
        panel.add(amountBox(210, "AMOUNT", "$1,342.92"));
        panel.add(amountBox(400, "AMOUNT", "$1,342.92"));
        panel.add(amountBox(590, "AMOUNT", "$1,342.92"));
        return panel;
    }

    public JPanel amountBox(int x, String title, String amount) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(x, 20, 180, 100);
        Border roundedBorder = new RoundedBorder(20, PRIMARY_BACKGROUND);
        panel.setBorder(roundedBorder);
        panel.setLayout(null);
        add(panel);

        JLabel heading = new JLabel(title);
        heading.setForeground(SUBHEADING_COLOR);
        heading.setBounds(10, 10, 180, heading.getPreferredSize().height);
        panel.add(heading);

        JLabel subHeading = new JLabel(amount);
        subHeading.setForeground(SECONDARY_BACKGROUND);
        subHeading.setFont(new Font(subHeading.getFont().getName(), Font.BOLD, 25));
        subHeading.setBounds(10, 30, 180, 25);
        panel.add(subHeading);

        return panel;
    }
}
