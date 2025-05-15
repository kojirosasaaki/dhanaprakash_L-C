package com.example.divisor_counter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DivisorCounterTest {

    private final DivisorCounter counter = new DivisorCounter();

    @Test
    void exampleTest() {
        assertEquals(3, counter.countMatchingDivisors(10));
    }

    @Test
    void zeroOrNegative() {
        assertEquals(0, counter.countMatchingDivisors(0));
        assertEquals(0, counter.countMatchingDivisors(-5));
    }

    @Test
    void smallNumbers() {
        assertEquals(0, counter.countMatchingDivisors(1));
        assertEquals(1, counter.countMatchingDivisors(2));
    }
}