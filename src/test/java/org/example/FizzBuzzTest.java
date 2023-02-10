package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {
    @Test
    void shouldReturnNumberAsAString() {
        //given
        FizzBuzz fizzBuzz = new FizzBuzz();
        // when
        String result = fizzBuzz.getNumber(1);
        //then
        assertEquals("1", result);
    }
}