package algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumbersTest {
    @Test
    void testIsAbundantNumber() {
        Numbers numbers = new Numbers();

        assertTrue(numbers.isAbundantNumber(24));
        assertTrue(numbers.isAbundantNumber(30));
        assertTrue(numbers.isAbundantNumber(36));
        assertTrue(numbers.isAbundantNumber(8085));
        assertFalse(numbers.isAbundantNumber(2));
        assertFalse(numbers.isAbundantNumber(7));
        assertFalse(numbers.isAbundantNumber(76785));
    }

    @Test
    void testIsAbundantNumberWithNegativeNumber() {
        Numbers numbers = new Numbers();
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> numbers.isAbundantNumber(-1));
        assertEquals("Number must be positive: -1", iae.getMessage());
        iae = assertThrows(IllegalArgumentException.class, () -> numbers.isAbundantNumber(-19));
        assertEquals("Number must be positive: -19", iae.getMessage());
    }

    @Test
    void testIsAbundantNumberWithZero() {
        Numbers numbers = new Numbers();
        assertFalse(numbers.isAbundantNumber(0));
    }
}