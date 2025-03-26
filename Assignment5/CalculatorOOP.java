package LandC;

import java.util.Scanner;

public class CalculatorOOP {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter operator (+, -, *, /, sqrt, cbrt, %, !, neg): ");
        String operator = sc.next();

        double num2 = 0;
        if (!operator.equals("sqrt") && !operator.equals("cbrt") && !operator.equals("!") && !operator.equals("neg")) {
            System.out.print("Enter second number: ");
            num2 = sc.nextDouble();
        }

      
        Calculation operation = null;

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
            case "/":
                operation = new Division();
                break;
            case "sqrt":
                operation = new SquareRoot();
                break;
            case "!":
                operation = new Factorial();
                break;
            case "%":
                operation = new Modulus();
                break;
            case "neg":
                operation = new Negation();
                break;
            case "^":
                operation = new PowerOf();
                break;
            default:
                System.out.println("Invalid operator.");
                break;
        }

  
        if (operation != null) {
            System.out.println("Result: " + operation.calculate(num1, num2));
        }

        sc.close();
    }
}
