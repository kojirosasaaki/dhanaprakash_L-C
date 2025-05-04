package Assignment1;

import java.util.*;

public class Marks {
    public static void main(String[] args) {
        List<SubjectMarks> marks1 = Arrays.asList(
            new SubjectMarks("english", 85),
            new SubjectMarks("biology", 90),
            new SubjectMarks("maths", 78)
        );
        List<SubjectMarks> marks2 = Arrays.asList(
            new SubjectMarks("english", 70),
            new SubjectMarks("biology", 88),
            new SubjectMarks("maths", 92)
        );
        List<SubjectMarks> marks3 = Arrays.asList(
            new SubjectMarks("english", 95),
            new SubjectMarks("biology", 80),
            new SubjectMarks("maths", 85)
        );

        List<Student> studentList = Arrays.asList(
            new Student("A", 101, marks1),
            new Student("B", 102, marks2),
            new Student("C", 103, marks3)
        );

        int selectedRoll = 102;

        StudentFinder finder = new StudentFinder();
        Student student = finder.findStudentByRoll(selectedRoll, studentList);

        if (student == null) {
            new StudentPrinter().printNotFound(selectedRoll);
        } else {
            MarksCalculator calculator = new MarksCalculator();
            int total = calculator.getTotal(student.marksList);
            double percentage = calculator.getPercentage(student.marksList);
            new StudentPrinter().printStudentDetails(student, total, percentage);
        }
    }
}

class SubjectMarks {
    String subject;
    int marks;

    public SubjectMarks(String subject, int marks) {
        this.subject = subject;
        this.marks = marks;
    }
}


class Student {
    String name;
    int rollno;
    List<SubjectMarks> marksList;

    public Student(String name, int rollno, List<SubjectMarks> marksList) {
        this.name = name;
        this.rollno = rollno;
        this.marksList = marksList;
    }
}


class StudentFinder {
    public Student findStudentByRoll(int rollno, List<Student> students) {
        for (Student s : students) {
            if (s.rollno == rollno) {
                return s;
            }
        }
        return null;
    }
}


class MarksCalculator {
    private final int maxMarksPerSubject = 100;

    public int getTotal(List<SubjectMarks> marksList) {
        int total = 0;
        for (SubjectMarks m : marksList) {
            total += m.marks;
        }
        return total;
    }

    public double getPercentage(List<SubjectMarks> marksList) {
        if (marksList.isEmpty()) return 0;
        int total = getTotal(marksList);
        return (total / (double) (marksList.size() * maxMarksPerSubject)) * 100;
    }
}


class StudentPrinter {
    public void printStudentDetails(Student student, int total, double percentage) {
        System.out.println("Name: " + student.name);
        System.out.println("Roll: " + student.rollno);
        System.out.println("Details:");
        for (SubjectMarks m : student.marksList) {
            System.out.println(" " + m.subject + ": " + m.marks);
        }
        System.out.println("Total: " + total);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("----------------------");
    }

    public void printNotFound(int rollno) {
        System.out.println("Not found: " + rollno);
    }
}

