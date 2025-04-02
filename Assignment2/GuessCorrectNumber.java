import java.util.Random;
import java.util.Scanner;

public class GuessCorrectNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int targetNumber = random.nextInt(100) + 1;
        int guessCount = 0;

        System.out.print("Guess a number between 1 and 100: ");
        int guessedNumber = scanner.nextInt();

        while (true) {
            if (isValidNumber(guessedNumber)) {
                guessCount++;

                if (guessedNumber > targetNumber) {
                    System.out.println("Too high! Guess again:");
                } else if (guessedNumber < targetNumber) {
                    System.out.println("Too low! Guess again:");
                } else {
                    System.out.println("You guessed it in " + guessCount + " attempts!");
                    break;
                }
            } else {
                System.out.print("Invalid guess. Please enter a number between 1 and 100: ");
            }

            guessedNumber = scanner.nextInt();
        }

        scanner.close();
    }

    public static boolean isValidNumber(int number) {
        return number > 0 && number <= 100;
    }
}
