import java.util.ArrayList;
import java.util.List;

class Marks {
    String subject;
    int marks;

    public Marks(String subject, int marks) {
        this.subject = subject;
        this.marks = marks;
    }

    public String getSubject() {
        return subject;
    }

    public int getMarks() {
        return marks;
    }
}

class Student {
    String name;
    int rollno;
    List<Marks> marksList;

    public Student(String name, int rollno, List<Marks> marksList) {
        this.name = name;
        this.rollno = rollno;
        this.marksList = marksList;
    }

    public String getName() {
        return name;
    }

    public int getRollno() {
        return rollno;
    }

    public List<Marks> getMarksList() {
        return marksList;
    }
}

public class Main {
    public static void display(int selectedRollNumber, List<Student> studentList) {
        int maxMarksPerSubject = 100;
        Student selectedStudent = null;

        for (Student student : studentList) {
            if (student.getRollno() == selectedRollNumber) {
                selectedStudent = student;
                break;
            }
        }

        if (selectedStudent == null) {
            System.out.println("Not found: " + selectedRollNumber);
            return;
        }

        int totalMarks = 0;
        for (Marks marks : selectedStudent.getMarksList()) {
            totalMarks += marks.getMarks();
        }

        int numberOfSubjects = selectedStudent.getMarksList().size();
        double percentage = (totalMarks / (double) (numberOfSubjects * maxMarksPerSubject)) * 100;

        System.out.println("Name: " + selectedStudent.getName());
        System.out.println("Roll: " + selectedStudent.getRollno());
        System.out.println("Details:");
        for (Marks marks : selectedStudent.getMarksList()) {
            System.out.println(" " + marks.getSubject() + ": " + marks.getMarks());
        }
        System.out.println("Total: " + totalMarks);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        Marks[] marks1 = {
            new Marks("english", 85),
            new Marks("biology", 90),
            new Marks("maths", 78)
        };
        Marks[] marks2 = {
            new Marks("english", 70),
            new Marks("biology", 88),
            new Marks("maths", 92)
        };
        Marks[] marks3 = {
            new Marks("english", 95),
            new Marks("biology", 80),
            new Marks("maths", 85)
        };

        Student[] studentList = {
            new Student("A", 101, marks1),
            new Student("B", 102, marks2),
            new Student("C", 103, marks3)
        };

        display(102, studentList);
    }
}
