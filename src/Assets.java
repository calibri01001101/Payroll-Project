import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Assets {
    // Colors that will be used for the entire project
    final Color SECONDARY_BACKGROUND = hexToColor("#E1E1E1");
    final Color PRIMARY_BACKGROUND = Color.WHITE;
    final Color HEADING_COLOR = hexToColor("#0D0D0D");
    final Color SUBHEADING_COLOR = hexToColor("#E1E1E1");

    // Converting the hexadecimal code to a color
    static Color hexToColor(String hex) {
        return Color.decode(hex);
    }

}
