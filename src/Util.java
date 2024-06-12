import javax.swing.*;
import java.text.DecimalFormat;

public class Util {
    private Util(){};
    public static void clearTextFieldText(JTextField textField) {
        textField.setText("");
    }

    public static String getTextFieldTextAndTrim(JTextField textField) {
        return textField.getText().trim();
    }


    public static void setLabelText(JLabel textField, String value){
        textField.setText(value);
    }

    public static String isUnknown(JTextField textField){
        // I used ternary operator here so if the value of the text field is empty it will show unknown
        return textField.getText().trim().isEmpty() ? "unknown" : textField.getText().trim();
    }

    public static boolean isEmpty(String str) {
        return !str.isEmpty();
    }

    public static double getDouble(JTextField textField) {
        return textField.getText().isEmpty() ? 0 : Double.parseDouble(textField.getText());
    }

    public static int getInt(JTextField textField) {
        return textField.getText().isEmpty() ? 0 : Integer.parseInt(textField.getText());
    }
    public static String numberFormatter(Double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(amount);
    }

}
