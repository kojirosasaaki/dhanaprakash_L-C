package Assignment1;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        new GameRunner().start();
    }
}

class InputValidator {
    public boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


class GuessingGameLogic {
    private final int targetNumber;
    private int guessCount = 0;

    public GuessingGameLogic() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
    }

    public boolean checkGuess(String input, InputValidator validator) {
        if (!validator.isNumber(input)) {
            System.out.print("I won't count this one. Please enter a number between 1 and 100: ");
            return false;
        }

        guessCount++;
        int guess = Integer.parseInt(input);

        if (guess < targetNumber) {
            System.out.print("Too low. Guess again: ");
        } else if (guess > targetNumber) {
            System.out.print("Too high. Guess again: ");
        } else {
            System.out.println("You guessed it in " + guessCount + " guesses!");
            return true;
        }
        return false;
    }
}


class GameRunner {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        InputValidator validator = new InputValidator();
        GuessingGameLogic game = new GuessingGameLogic();

        System.out.print("Guess a number between 1 and 100: ");
        String userInput = scanner.nextLine();

        while (true) {
            if (game.checkGuess(userInput, validator)) {
                break;
            }
            userInput = scanner.nextLine();
        }

        scanner.close();
    }
}

