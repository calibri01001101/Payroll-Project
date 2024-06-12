public class CalculatorUtils {

    private CalculatorUtils() {}

    public static double grossPayCalculator(double basic, double overtime, double holiday) {
        return basic + overtime + holiday;
    }

    public static double netPayCalculator(double grossPay, double deductions) {
        return grossPay - deductions;
    }

    public static double basicPay(int daysWorked, double salaryRate) {
        return daysWorked * salaryRate;
    }

    public static double overtime(int hoursOvertime, double salaryRate) {
        return hoursOvertime * ((salaryRate / 8) * 1.1);
    }

    public static double holidayPay(int regHolidays, int specialHoliday, double salaryRate) {
        double regularHolidayPay = (salaryRate + (salaryRate * 0.3)) * regHolidays;
        double specialHolidayPay = salaryRate * specialHoliday;
        return regularHolidayPay + specialHolidayPay;
    }

    public static double lateDeduction(int hoursLate, double salaryRate) {
        return (salaryRate / 8) * hoursLate;
    }

    public static double baleDeduction(double bale) {
        return bale;
    }

    public static double tinDeduction() {
        return 0;
    }

    public static double pagibigDeduction() {
        return 200;
    }

    public static double philHealthDeduction() {
        return 100;
    }

    public static double sssDeduction(double grossPay) {
        return grossPay * 0.1;
    }

    public static double totalDeductions(double tin, double pagIbig, double sss, double philHealth, double late, double bale) {
        return tin + pagIbig + sss + philHealth + late + bale;
    }
}
