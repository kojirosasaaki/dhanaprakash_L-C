package Assignment1;

import java.util.Scanner;
import java.util.Random;

public class DiceRoller {
    public static void main(String[] args) {
        DiceInputHandler inputHandler = new DiceInputHandler();
        DiceOutputHandler outputHandler = new DiceOutputHandler();
        Dice dice = new Dice(6);

        boolean continueRolling = true;

        while (continueRolling) {
            continueRolling = inputHandler.shouldContinue();
            if (continueRolling) {
                int roll = dice.roll();
                outputHandler.showRoll(roll);
            }
        }

        outputHandler.showExitMessage();
        inputHandler.closeScanner();
    }
}


class DiceInputHandler {
    private Scanner scanner = new Scanner(System.in);

    public boolean shouldContinue() {
        System.out.print("Ready to roll? Enter Q to Quit: ");
        String input = scanner.nextLine();
        return !input.equalsIgnoreCase("q");
    }

    public void closeScanner() {
        scanner.close();
    }
}


class Dice {
    private int sides;
    private Random random;

    public Dice(int sides) {
        this.sides = sides;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(sides) + 1;
    }
}


class DiceOutputHandler {
    public void showRoll(int roll) {
        System.out.println("You have rolled a " + roll);
    }

    public void showExitMessage() {
        System.out.println("Thank you for playing!");
    }
}



