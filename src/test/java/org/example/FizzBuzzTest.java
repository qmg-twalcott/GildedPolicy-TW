package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {
    @Test
    void shouldReturnNumber1AsAString() {
        //given
        FizzBuzz fizzBuzz = new FizzBuzz();
        // when
        String result = fizzBuzz.getNumber(1);
        //then
        assertEquals("1", result);
    }

    @Test
    void shouldReturnNumber2AsString() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        //when
        String result = fizzBuzz.getNumber(2);
        //then
        assertEquals("2", result);
    }


    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9, 12, 15})
    void shouldReturnFizzForOf3(int number) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        //when
        String result = fizzBuzz.getNumber(number);
        //then
        assertEquals("fizz", result);
    }

}