import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileFunctions {
    public static HashMap<String, Employee> get(){

        HashMap<String, Employee> employeeList = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("employees_details"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                employeeList.put(data[0].trim(), new Employee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9])));
            }
            reader.close();
        }catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
        return employeeList;
    }
    public static void update(String key, Employee employeeDetails) {
        HashMap<String, Employee> employeeList = get();
        employeeList.put(key, employeeDetails);
        writer(employeeList);
    }
    public static void delete(String key){
        HashMap<String, Employee> employeeList = get();
        employeeList.remove(key);
        writer(employeeList);
    }
    public static void create(String fileName, Employee employeeDetails) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true) );
            writer.write(String.format("%s|%s|%s|%s|%s|%s|%s|0|0|0\n",
                    employeeDetails.getFullName(),
                    employeeDetails.getPhoneNumber(),
                    employeeDetails.getPosition(),
                    employeeDetails.getSss(),
                    employeeDetails.getTin(),
                    employeeDetails.getPagIbig(),
                    employeeDetails.getPhilHeath()
            ));
            writer.close();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static boolean writer(HashMap<String, Employee> employeeList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("employees_details"));
            for(Map.Entry<String, Employee> employee : employeeList.entrySet()) {
                writer.write(String.format("%s|%s|%s|%s|%s|%s|%s|%.2f|%.2f|%.2f\n",
                        employee.getValue().getFullName(),
                        employee.getValue().getPhoneNumber(),
                        employee.getValue().getPosition(),
                        employee.getValue().getSss(),
                        employee.getValue().getTin(),
                        employee.getValue().getPhilHeath(),
                        employee.getValue().getPagIbig(),
                        employee.getValue().getGrossPay(),
                        employee.getValue().getTotalDeductions(),
                        employee.getValue().getNetPay()
                ));
            }
            writer.close();
            return true;
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


}
