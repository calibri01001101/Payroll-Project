import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    // JLayeredPane that will hold the page to show
    JLayeredPane layeredPane;
    public MainFrame() {
        // Set the title for the window
        super("Payroll System");
        // To close the window when exit button is clicked
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Setting the size of the window
        setSize(1000, 600);
        // To open the gui window at the center of the screen
        setLocationRelativeTo(null);
        // To move freely the components
        setLayout(null);
        // For the window to become not resizable
        setResizable(false);

        initializeComponents();
    }

    // Initializing the components that need to be shown on screen
    public void initializeComponents() {
        sideBar();
        pagesHolder();
    }

    // First page to show when the app opens
    public void pagesHolder() {
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(200, 0, 800, 600);
        add(layeredPane);
        Dashboard dashboardPage = new Dashboard();
        layeredPane.add(dashboardPage.dashboardPage());
    }

    // Sidebar that contains the options where the user can navigate
    public void sideBar() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 200, 600);
        panel.setBackground(Assets.PRIMARY_BACKGROUND);
        panel.setLayout(null);
        add(panel);
        // Creating an instance of the sidebar button/texts
        sideBarButton(panel, "Dashboard", 40);
        sideBarButton(panel, "Manage Employees", 70);
        sideBarButton(panel, "Payroll Manager", 100);
        sideBarButton(panel, "Payroll List", 130);

    }

    // Sidebar label selection
    public void sideBarButton(JPanel panel, String title, int y) {
        JLabel label = new JLabel(title);
        label.setForeground(Assets.WHITE);
        label.setBounds(20, y, 200, 25);
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 18));

        // When a label is clicked it will show the page for it
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(label.getText().equals("Manage Employees")) {
                    ManageEmployees addEmployeePage = new ManageEmployees();
                    // Removing the current layered pane
                    layeredPane.removeAll();
                    // adding the pane that is clicked
                    layeredPane.add(addEmployeePage.addEmployeePage());
                    // Refreshing the page
                    layeredPane.repaint();
                    layeredPane.revalidate();
                }
                if(label.getText().equals("Dashboard")) {
                    Dashboard dashboardPage = new Dashboard();
                    layeredPane.removeAll();
                    layeredPane.add(dashboardPage.dashboardPage());
                    layeredPane.repaint();
                    layeredPane.revalidate();
                }
                if(label.getText().equals("Payroll Manager")) {
                    PayrollManager payrollManagerPage = new PayrollManager();
                    layeredPane.removeAll();
                    layeredPane.add(payrollManagerPage.payrollPage());
                    label.repaint();
                    layeredPane.revalidate();
                }
                if(label.getText().equals("Payroll List")) {
                    PayrollList payrollList = new PayrollList();
                    layeredPane.removeAll();
                    layeredPane.add(payrollList.payrollList());
                    label.repaint();
                    layeredPane.revalidate();
                }
            }
        });
        panel.add(label);
    }


}