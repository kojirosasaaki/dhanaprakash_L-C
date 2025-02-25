public class Employee {
    private String name;
    private int age;
    private double salary;

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for age
    public void setAge(int age) {
        this.age = age;
    }

    // Getter for salary
    public double getSalary() {
        return salary;
    }

    // Setter for salary
    public void setSalary(double salary) {
        this.salary = salary;
    }
}


/*Employee is a data structure but not an Object. Cause in Object we hide the implementation only show functionality.
 In employee, there are getters and setters which are used to have access, that's not how it is suppose to be in Object.
 It is clearly data structure.
*/