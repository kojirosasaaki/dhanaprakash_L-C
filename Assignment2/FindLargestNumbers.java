package Assignment2;

import java.util.Scanner;

public class FindLargestNumbers {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        ArrayPrinter arrayPrinter = new ArrayPrinter();
        LargestFinder finder = new LargestFinder();

        int[] numbers = inputHandler.readNumbers();
        arrayPrinter.printArray(numbers);

        int[] result = finder.findTwoLargest(numbers);

        System.out.println("1st largest number: " + result[0]);
        System.out.println("2nd largest number: " + result[1]);

        inputHandler.close();
    }
}

class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public int[] readNumbers() {
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] numbers = new int[size];

        System.out.println("Enter " + size + " numbers into the array: ");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        return numbers;
    }

    public void close() {
        scanner.close();
    }
}


class ArrayPrinter {
    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element at index " + i + ": " + array[i]);
        }
    }
}


class LargestFinder {
    public int[] findTwoLargest(int[] numbers) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : numbers) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }

        return new int[]{first, second};
    }
}

