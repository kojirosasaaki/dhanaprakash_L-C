package LandC;

import java.util.Scanner;

public class CalculatorOOP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter operator (+, -, *): ");
        String operator = sc.next();

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        Calculation operation = null; // Default null to handle invalid cases

        switch (operator) {
            case "+":
                operation = new Add();
                break;
            case "-":
                operation = new Subtract();
                break;
            case "*":
                operation = new Multiply();
                break;
            default:
                System.out.println("Invalid operator.");
                break; // Break for default case too
        }

        if (operation != null)
            System.out.println("Result: " + operation.calculate(num1, num2));

        sc.close();
    }
}
