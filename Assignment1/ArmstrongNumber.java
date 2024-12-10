import java.util.Scanner;

public class ArmstrongNumber {
    public static int getArmstrongNumber(int number) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPlease enter the number to check for Armstrong: ");
        int userInput = Integer.parseInt(scanner.nextLine());

        if (userInput <= 0) {
            System.out.println("Invalid input. Please enter a positive integer.");
        } else {
            if (userInput == getArmstrongNumber(userInput)) {
                System.out.println("\n" + userInput + " is an Armstrong number.\n");
            } else {
                System.out.println("\n" + userInput + " is not an Armstrong number.\n");
            }
        }

        scanner.close();
    }
}
