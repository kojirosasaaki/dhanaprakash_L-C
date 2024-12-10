import java.util.Scanner;
import java.util.Random;

public class DiceRoller {
    public static int rollDie(int sides) {
        Random random = new Random();
        return random.nextInt(sides) + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sides = 6;
        boolean continueRolling = true;

        while (continueRolling) {
            System.out.print("Ready to roll? Enter Q to Quit: ");
            String input = scanner.nextLine();

            if (!input.equalsIgnoreCase("q")) {
                int rollDie = rollDie(sides);
                System.out.println("You have rolled a " + rollDie);
            } else {
                continueRolling = false;
            }
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
