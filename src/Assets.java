import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Assets {
    // Colors that will be used for the entire project
    final String BLACK_HEX = "#0D0D0D";
    final String SECONDARY_WHITE = "#E1E1E1";
    final String TERTIARY_WHITE = "#A1A1A1";
    final String GREEN = "#28a745";
    final Color SECONDARY_BACKGROUND = hexToColor(SECONDARY_WHITE);
    final Color PRIMARY_BACKGROUND = Color.WHITE;
    final Color HEADING_COLOR = hexToColor(BLACK_HEX);
    final Color SUBHEADING_COLOR = hexToColor(SECONDARY_WHITE);
    final Color SPAN_COLOR = hexToColor(TERTIARY_WHITE);
    final Color SUCCESS = hexToColor(GREEN);



    // Converting the hexadecimal code to a color
    static Color hexToColor(String hex) {
        return Color.decode(hex);
    }

    // CSV READER
    static String[][] fileReader(String fileLocation) {
        List<String[]> dataList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                dataList.add(data);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList.toArray(new String[0][]);
    }



}
