import javax.swing.*;
import java.awt.Color;

public class Assets {
    // Colors that will be used for the entire project
    public static final Color SECONDARY_BACKGROUND = hexToColor("#93C5FD");
    public static final Color PRIMARY_BACKGROUND = hexToColor("#2563EB");
    public static final Color WHITE = hexToColor("#FFFFFF");
    public static final Color SUBHEADING_COLOR = hexToColor("#E1E1E1");

    // Private constructor to prevent instantiation
    private Assets() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // Converting the hexadecimal code to a color
    public static Color hexToColor(String hex) {
        return Color.decode(hex);
    }

    public static void clearTextFieldText(JTextField textField) {
        textField.setText("");
    }

}
