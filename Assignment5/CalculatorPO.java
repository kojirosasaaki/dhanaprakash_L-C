package LandC;

import java.util.Scanner;

public class CalculatorPO {
    public static double calculate(double a, String op, double b) {
        double result = Double.NaN;

        switch (op) {
            case "+":
                result = a + b;
                break;

            case "-":
                result = a - b;
                break;

            case "*":
                result = a * b;
                break;

            case "/":
                if (b != 0) {
                    result = a / b;
                }
                break;

            default:
                System.out.println("Invalid operator.");
                break;
        }
        
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();
        
        System.out.print("Enter operator (+, -, * , /): ");
        String operator = sc.next();
        
        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        double result = calculate(num1, operator, num2);
        System.out.println("Result: " + result);
        
        sc.close();
    }
}
