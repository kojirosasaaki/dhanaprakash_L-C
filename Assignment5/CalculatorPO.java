package LandC;

import java.util.Scanner;

public class CalculatorPO {
    
    public static double squareRoot(double a) {
        if (a < 0) {
            System.out.println("Square root of a negative number is undefined.");
            return Double.NaN;
        }
        if (a == 0) 
        	return 0; 
        double assumedNumber = a / 2; 
        for (int i = 0; i < 10; i++) {
        	assumedNumber = (assumedNumber + a / assumedNumber) / 2; 
        }
        return assumedNumber; 
    }

  
    public static double power(double a, double b) {
        double result = 1;
        for (int i = 0; i < b; i++) {
            result *= a; 
        }
        return result;
    }

   
    public static double negate(double a) {
        return -a; 
    }

   
    public static double modulus(double a, double b) {
        if (b == 0) {
            System.out.println("Division by zero in modulus is undefined.");
            return Double.NaN;
        }
        return a - (b * (int) (a / b)); 
    }

    
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
                } else {
                    System.out.println("Division by zero is undefined.");
                }
                break;
            case "^":
                result = power(a, b);
                break;
            case "sqrt":
                result = squareRoot(a);
                break;
            case "!":
                if (a < 0) {
                    System.out.println("Factorial of negative numbers is not defined.");
                } else {
                    result = 1;
                    for (int i = 1; i <= a; i++) {
                        result *= i;
                    }
                }
                break;
            case "%":
                result = modulus(a, b);
                break;
            case "neg":
                result = negate(a);
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

        System.out.print("Enter operator (+, -, *, /, ^, sqrt, !, %, neg): ");
        String operator = sc.next();

        double num2 = 0;
        if (!(operator.equals("sqrt") || operator.equals("!") || operator.equals("neg"))) {
            System.out.print("Enter second number: ");
            num2 = sc.nextDouble();
        }

        double result = calculate(num1, operator, num2);
        System.out.println("Result: " + result);

        sc.close();
    }
}
