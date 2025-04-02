package LandC;

class Employee {
    private String name;
    private int age;
    private float salary;

    public Employee(String name, int age, float salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void increaseSalary(float amount) {
        if (amount > 0) {
            salary += amount;
            System.out.println("Salary increased. New Salary: " + salary);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void displayEmployee() {
        System.out.println("Employee: " + name + ", Age: " + age + ", Salary: " + salary);
    }
}

public class EmployeeTest {
    public static void main(String[] args) {
        Employee employee = new Employee("Alice", 25, 50000);
        employee.displayEmployee();
        employee.increaseSalary(5000);
    }
}

/*
Employee class is just a data structure because it only stores and shows data using getters and setters.An objects should 
contain both data and meaningful operations to maintain encapsulation and abstraction.*/