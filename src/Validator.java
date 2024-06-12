import javax.swing.*;
import java.awt.*;

public class Validator {
    private Validator(){};

    public static void forbidden(Component component, String message) {
        JOptionPane.showMessageDialog(component, message);
    }

    public static void success(Component component, String message) {
        JOptionPane.showMessageDialog(component, message);
    }

    public static void error(Component component, String message) {
        JOptionPane.showMessageDialog(component, message);
    }
}