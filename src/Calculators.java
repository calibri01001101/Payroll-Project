public interface Calculators {
    static double grossPayCalculator(double basic, double overtime, double holiday) {
        return basic + overtime + holiday;
    }
    // Function to calculate the government deductions
    static double netPayCalculator(double grossPay, double deductions) {
        return grossPay - deductions;
    }

    static double basicPay(int daysWorked, double salaryRate) {
        return daysWorked * salaryRate;
    }

    static double overtime(int hoursOvertime, double salaryRate) {
        return hoursOvertime * ((salaryRate / 8) * 1.1);
    }

    static double holidayPay(int regHolidays, int specialHoliday, double salaryRate) {
        double regularHolidayPay = (salaryRate + (salaryRate * 0.3)) * regHolidays;
        double specialHolidayPay = salaryRate * specialHoliday;
        return regularHolidayPay + specialHolidayPay;
    }

    static double lateDeduction(int hoursLate, double salaryRate) {
        return (salaryRate / 8) * hoursLate;
    }

    static double baleDeduction(double bale) {
        return bale;
    }

    static double tinDeduction() {
        return 0;
    }

    static double pagibigDeduction() {
        return 100;
    }

    static double philHealthDeduction() {
        return 100;
    }

    static double sssDeduction(double grossPay) {
        return grossPay * 0.1;
    }

    static double totalDeductions(double tin, double pagIbig, double sss, double philHealth, double late, double bale) {
        return tin + pagIbig + sss + philHealth + late + bale;
    }

}
