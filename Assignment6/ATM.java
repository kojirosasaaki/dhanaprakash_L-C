package Assignment6;

import java.util.Scanner;

public class ATM {

	private static final String CORRECT_PIN = "1234";
	private static final int DAILY_WITHDRAWAL_LIMIT = 20000;
	private static final int MAX_PIN_ATTEMPTS = 3;

	private static int accountBalance = 50000;
	private static int atmCash = 100000;
	private static int dailyWithdrawnAmount = 0;
	private static int pinAttempts = 0;
	private static boolean isCardBlocked = false;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			connectToServer();
			authenticateUser(scanner);
			processWithdrawal(scanner);
		} catch (ArithmeticException e) {
			System.out.println("Arithmetic Error: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Number Format Error: " + e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("Null Pointer Error: " + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index Out Of Bounds Error: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument Error: " + e.getMessage());
		} catch (IllegalStateException e) {
			System.out.println("Illegal State Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("General Error: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	private static void connectToServer() {
		boolean serverConnected = true;// if you want to trigger this error then serverConnected to false
		if (!serverConnected) {
			throw new IllegalStateException("Unable to connect to the server. Please try again later.");
		}
	}

	private static void authenticateUser(Scanner scanner) {
		while (pinAttempts < MAX_PIN_ATTEMPTS) {
			System.out.print("Enter your PIN: ");
			String enteredPin = scanner.nextLine();

			if (enteredPin == null) {
				throw new NullPointerException("PIN entry cannot be null.");
			}

			if (isCardBlocked) {
				throw new IllegalStateException("Your card is blocked due to multiple incorrect PIN attempts.");
			}

			if (CORRECT_PIN.equals(enteredPin)) {
				return;
			} else {
				pinAttempts++;
				if (pinAttempts == MAX_PIN_ATTEMPTS) {
					isCardBlocked = true;
					throw new IllegalStateException("Your card is blocked due to multiple incorrect PIN attempts.");
				} else {
					System.out.println("Incorrect PIN. Please try again.");
				}
			}
		}
	}

	private static void processWithdrawal(Scanner scanner) {
		System.out.print("Enter amount to withdraw: ₹");
		int amount;

		try {
			amount = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid amount entered. Transaction cancelled.");
		}

		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
		}

		if (amount > atmCash) {
			throw new IllegalStateException("ATM does not have sufficient cash.");
		}

		if (amount > accountBalance) {
			throw new IllegalStateException("Insufficient account balance.");
		}

		if ((dailyWithdrawnAmount + amount) > DAILY_WITHDRAWAL_LIMIT) {
			throw new IllegalStateException("Daily withdrawal limit exceeded.");
		}

		accountBalance -= amount;
		atmCash -= amount;
		dailyWithdrawnAmount += amount;

		System.out.println("Please collect your cash: ₹" + amount);
		System.out.println("Remaining account balance: ₹" + accountBalance);
	}
}
