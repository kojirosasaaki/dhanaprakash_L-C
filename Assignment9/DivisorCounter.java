package com.example.divisor_counter;

public class DivisorCounter {
    public int countMatchingDivisors(int n) {
        if (n < 1) return 0;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (countDivisors(i) == countDivisors(n - i)) {
                count++;
            }
        }
        return count;
    }

    private int countDivisors(int num) {
        if (num <= 0) return 0;
        int result = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                result += (i == num / i) ? 1 : 2;
            }
        }
        return result;
    }
}

