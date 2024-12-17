import java.util.Random;
import java.util.Scanner;

public class GuessCorrectNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		Random random = new Random();
		int targetnumber = random.nextInt(101);
		
		System.out.println("Guess a number between 1 and 100 : ");
		int originalnumber = scanner.nextInt();
		
		int guessCount = 0;
		
		while(true)
			if(isValidNumber(originalnumber)) {
				
				guessCount++;
				
				if(originalnumber > targetnumber) {
					System.out.println("Too high guess again");
				}
				else if(originalnumber < targetnumber) {
					System.out.println("Too low guess again");
				}
				else {
					System.out.println("You guessed it in " + guessCount + " guesses!");
				}
				originalnumber = scanner.nextInt();
				
			}
			else {
				
				System.out.print("I won't count this one. Please guess again number between 1 and 100: ");
				originalnumber = scanner.nextInt();
				continue;
			}
		
	}
	public static boolean isValidNumber(int originalnumber) {
		if(originalnumber > 0 && originalnumber <= 100) {
			return true;
		}
		else {
		return false;
		}
	}
}
