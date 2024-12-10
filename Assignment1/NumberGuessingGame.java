import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int randomNo = random.nextInt(100) + 1;
        boolean guessed = false;
        int guessCount = 0;

        System.out.print("Guess a number between 1 and 100: ");
        String userGuess = scanner.nextLine();

        while (!guessed) {
            if (!isNumber(userGuess)) {
                System.out.print("I won't count this one. Please enter a number between 1 and 100: ");
                userGuess = scanner.nextLine();
                continue;
            }

            guessCount++;
            int guess = Integer.parseInt(userGuess);

            if (guess < randomNo) {
                System.out.print("Too low. Guess again: ");
                userGuess = scanner.nextLine();
            } else if (guess > randomNo) {
                System.out.print("Too high. Guess again: ");
                userGuess = scanner.nextLine();
            } else {
                System.out.println("You guessed it in " + guessCount + " guesses!");
                guessed = true;
            }
        }

        scanner.close();
    }
}
