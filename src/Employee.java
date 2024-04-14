public class Employee {
    private String fullName;
    private String phoneNumber;
    private String position;
    private String sss;
    private String tin;
    private String philHeath;
    private String pagIbig;
    private double netPay;
    private double grossPay;
    private double totalDeductions;

    public Employee(String fullName, String phoneNumber, String position, String sss, String tin, String philHeath, String pagIbig, double grossPay, double totalDeductions, double netPay) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.sss = sss;
        this.tin = tin;
        this.philHeath = philHeath;
        this.pagIbig = pagIbig;
        this.netPay = netPay;
        this.grossPay = grossPay;
        this.totalDeductions = totalDeductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSss() {
        return sss;
    }

    public void setSss(String sss) {
        this.sss = sss;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getPhilHeath() {
        return philHeath;
    }

    public void setPhilHeath(String philHeath) {
        this.philHeath = philHeath;
    }

    public String getPagIbig() {
        return pagIbig;
    }

    public void setPagIbig(String pagIbig) {
        this.pagIbig = pagIbig;
    }
}
