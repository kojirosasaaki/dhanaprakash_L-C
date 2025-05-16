package com.example.divisor_counter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DivisorCounterTest {

    private final DivisorCounter counter = new DivisorCounter();

    @Test
    void returnsOneWhenInputIsTen() {
        int result = counter.countMatchingDivisors(10);
        assertEquals(1, result);
    }

    @Test
    void returnsZeroWhenInputIsZero() {
        int result = counter.countMatchingDivisors(0);
        assertEquals(0, result);
    }

    @Test
    void returnsZeroWhenInputIsNegative() {
        int result = counter.countMatchingDivisors(-5);
        assertEquals(0, result);
    }

    @Test
    void returnsZeroWhenInputIsOne() {
        int result = counter.countMatchingDivisors(1);
        assertEquals(0, result);
    }

    @Test
    void returnsZeroWhenInputIsTwo() {
        int result = counter.countMatchingDivisors(2);
        assertEquals(0, result);
    }
}

