import java.util.Scanner;

public class FindLargestNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int arraySize = scanner.nextInt();

        int[] numbers = new int[arraySize];

        System.out.println("Enter " + arraySize + " numbers into the array: ");
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = scanner.nextInt();
        }

        int index = 0;
        for (int num : numbers) {
            System.out.println("Element at index " + index + ": " + num);
            index++;
        }

        int[] largestNumbers = findFirstAndSecondLargest(numbers);

        System.out.println("1st largest number: " + largestNumbers[0]);
        System.out.println("2nd largest number: " + largestNumbers[1]);

        scanner.close();
    }

    public static int[] findFirstAndSecondLargest(int[] numbers) {
        int firstLargest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : numbers) {
            if (num > firstLargest) {
                secondLargest = firstLargest;
                firstLargest = num;
            } else if (num > secondLargest && num != firstLargest) {
                secondLargest = num;
            }
        }

        return new int[]{firstLargest, secondLargest};
    }
}
