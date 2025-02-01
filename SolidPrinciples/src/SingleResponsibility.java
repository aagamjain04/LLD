// bad example - violating SRP
class Employee {
    private String name;
    private String email;
    private double salary;

    public Employee(String name, String email,double salary) {
        this.salary = salary;
        this.email = email;
        this.name = name;
    }

    // Employee data management
    public void saveEmployee() {
        // Database connection code
        System.out.println("Saving employee to database...");
        // SQL queries to save employee
    }

    // Salary calculation
    public double calculateTaxDeduction() {
        System.out.println("Salary Calculation...");
        return salary * 0.2;
    }

    // Email functionality
    public void sendPayslip() {

        System.out.println("Connecting to email server...");
        String message = "Dear " + name + ", please find your payslip attached...";
        // Code to send email
    }

}

// Good example - following SRP
class Employee1 {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private String email;
    private double salary;

    public Employee1(String name, String email, double salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }


    // Simple getters and setters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
}

class EmployeeRepository {
    public void save(Employee1 employee) {
        // Database connection code
        System.out.println("Saving employee to database...");
        // SQL queries to save employee
    }

    public Employee1 findById(int id) {
        // Code to retrieve employee from database
        return null;
    }
}

class SalaryCalculator {
    public double calculateTaxDeduction(Employee1 employee) {
        return employee.getSalary() * 0.2; // 20% tax
    }

    public double calculateNetSalary(Employee1 employee) {
        System.out.println("Salary Calculation...");
        double tax = calculateTaxDeduction(employee);
        return employee.getSalary() - tax;
    }
}

class EmailService {
    public void sendPayslip(Employee1 employee, double netSalary) {
        // Email server configuration
        System.out.println("Connecting to email server...");
        String message = "Dear " + employee.getName() +
                ", please find your payslip attached..." +
                "\nNet Salary: " + netSalary;
        // Code to send email
    }
}




public class SingleResponsibility {
    public static void main(String[] args) {


        //violating SRP
        System.out.println("Violating SRP");
        Employee employee = new Employee("John Doe", "john@example.com", 50000);
        employee.saveEmployee();
        employee.calculateTaxDeduction();
        employee.sendPayslip();


        //following SRP
        System.out.println("Following SRP");
        Employee1 employee1 = new Employee1("John Doe", "john@example.com", 50000);
        EmployeeRepository repository = new EmployeeRepository();
        SalaryCalculator calculator = new SalaryCalculator();
        EmailService emailService = new EmailService();
        repository.save(employee1);
        double netSalary = calculator.calculateNetSalary(employee1);
        emailService.sendPayslip(employee1, netSalary);
    }
}