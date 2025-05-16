package com.example.divisor_counter;

public class DivisorCounter {

    public int countMatchingDivisors(int input) {
        if (input < 1) {
            return 0;
        }

        int matchingPairs = 0;

        for (int i = 1; i < input; i++) {
            int firstDivisorCount = countDivisors(i);
            int secondDivisorCount = countDivisors(i+1);

            if (firstDivisorCount == secondDivisorCount) {
                matchingPairs++;
            }
        }

        return matchingPairs;
    }

    private int countDivisors(int number) {
        if (number <= 0) {
            return 0;
        }

        int totalDivisors = 0;

        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
            	totalDivisors++;
            }
        }

        return totalDivisors;
    }

}


