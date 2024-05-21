import java.awt.*;

public interface Assets {
    // Colors that will be used for the entire project
    Color SECONDARY_BACKGROUND = hexToColor("#93C5FD");
    Color PRIMARY_BACKGROUND = hexToColor("#2563EB");
    Color WHITE = hexToColor("#FFFFFF");
    Color SUBHEADING_COLOR = hexToColor("#E1E1E1");

    // Converting the hexadecimal code to a color
    static Color hexToColor(String hex) {
        return Color.decode(hex);
    }

}
