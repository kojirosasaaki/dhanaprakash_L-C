package Assignment2;

import java.util.Random;
import java.util.Scanner;

public class GuessCorrectNumber {
    public static void main(String[] args) {
        NumberGenerator generator = new NumberGenerator();
        InputReader reader = new InputReader();
        NumberValidator validator = new NumberValidator();

        int target = generator.generateTargetNumber();
        GameEngine game = new GameEngine(target, reader, validator);

        game.play();
        reader.close();
    }
}

class NumberGenerator {
    public int generateTargetNumber() {
        return new Random().nextInt(100) + 1;
    }
}


class InputReader {
    private final Scanner scanner = new Scanner(System.in);

    public int getUserGuess() {
        System.out.print("Guess a number between 1 and 100: ");
        return scanner.nextInt();
    }

    public void close() {
        scanner.close();
    }
}


class NumberValidator {
    public boolean isValid(int number) {
        return number > 0 && number <= 100;
    }
}


class GameEngine {
    private final int targetNumber;
    private final InputReader reader;
    private final NumberValidator validator;

    public GameEngine(int targetNumber, InputReader reader, NumberValidator validator) {
        this.targetNumber = targetNumber;
        this.reader = reader;
        this.validator = validator;
    }

    public void play() {
        int guess;
        int attempts = 0;

        while (true) {
            guess = reader.getUserGuess();

            if (!validator.isValid(guess)) {
                System.out.println("Invalid guess. Please enter a number between 1 and 100.");
                continue;
            }

            attempts++;

            if (guess > targetNumber) {
                System.out.println("Too high! Guess again:");
            } else if (guess < targetNumber) {
                System.out.println("Too low! Guess again:");
            } else {
                System.out.println("You guessed it in " + attempts + " attempts!");
                break;
            }
        }
    }
}

