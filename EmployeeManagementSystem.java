import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple console application for managing employee records.
 * Compile with: javac EmployeeManagementSystem.java
 * Run with:     java EmployeeManagementSystem
 */
public class EmployeeManagementSystem {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Employee> EMPLOYEES = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add employee");
            System.out.println("2. Display all employees");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Exit");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Please choose an option from 1 to 4.");
            }
        }

        SCANNER.close();
    }

    private static void addEmployee() {
        int id = readInt("Employee ID: ");
        if (findEmployeeById(id) != null) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        String name = readNonEmptyLine("Name: ");
        String department = readNonEmptyLine("Department: ");
        double salary = readPositiveDouble("Salary: ");

        EMPLOYEES.add(new Employee(id, name, department, salary));
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {
        if (EMPLOYEES.isEmpty()) {
            System.out.println("No employee records to display.");
            return;
        }

        System.out.println("\nEmployee records:");
        System.out.printf("%-12s %-24s %-20s %12s%n", "ID", "Name", "Department", "Salary");
        System.out.println("-----------------------------------------------------------------------");
        for (Employee employee : EMPLOYEES) {
            employee.display();
        }
    }

    private static void searchEmployee() {
        int id = readInt("Enter the employee ID to search for: ");
        Employee employee = findEmployeeById(id);

        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            System.out.println("Employee found:");
            System.out.printf("%-12s %-24s %-20s %12s%n", "ID", "Name", "Department", "Salary");
            employee.display();
        }
    }

    private static Employee findEmployeeById(int id) {
        for (Employee employee : EMPLOYEES) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = SCANNER.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                // Show the common validation message below.
            }
            System.out.println("Please enter a valid non-negative salary.");
        }
    }

    private static String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = SCANNER.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static final class Employee {
        private final int id;
        private final String name;
        private final String department;
        private final double salary;

        private Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        private int getId() {
            return id;
        }

        private void display() {
            System.out.printf("%-12d %-24s %-20s %12.2f%n", id, name, department, salary);
        }
    }
}
