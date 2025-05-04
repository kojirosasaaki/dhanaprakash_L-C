package Assignment1;

import java.util.Scanner;

public class ArmstrongNumber {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        ArmstrongCalculator calculator = new ArmstrongCalculator();
        OutputPrinter outputPrinter = new OutputPrinter();

        int userInput = inputReader.readNumber();
        boolean result = calculator.isArmstrong(userInput);
        outputPrinter.printResult(userInput, result);
    }
}

class InputReader {
    public int readNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPlease enter the number to check for Armstrong: ");
        int number = Integer.parseInt(scanner.nextLine());
        scanner.close();
        return number;
    }
}

class ArmstrongCalculator {
    public int getArmstrongNumber(int number) {
        int armstrongNumber = 0;
        int digits = 0;
        int temporaryInput = number;

        while (temporaryInput > 0) {
            digits++;
            temporaryInput /= 10;
        }

        temporaryInput = number;

        while (temporaryInput > 0) {
            int remainder = temporaryInput % 10;
            armstrongNumber += Math.pow(remainder, digits);
            temporaryInput /= 10;
        }

        return armstrongNumber;
    }

    public boolean isArmstrong(int number) {
        return number == getArmstrongNumber(number);
    }
}


class OutputPrinter {
    public void printResult(int number, boolean isArmstrong) {
        if (number <= 0) {
            System.out.println("Invalid input. Please enter a positive integer.");
        } else if (isArmstrong) {
            System.out.println("\n" + number + " is an Armstrong number.\n");
        } else {
            System.out.println("\n" + number + " is not an Armstrong number.\n");
        }
    }
}



