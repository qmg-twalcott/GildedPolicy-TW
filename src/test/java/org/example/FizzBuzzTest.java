package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    private final FizzBuzz fizzBuzz = new FizzBuzz();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 7, 8})
    void shouldReturnNumberAsAString(int number) {
        // when
        String result = fizzBuzz.getNumber(number);
        //then
        assertEquals(Integer.toString(number), result);
    }


    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9, 12, 15})
    void shouldReturnFizzForMultiplesOf3(int number) {
        //when
        String result = fizzBuzz.getNumber(number);
        //then
        assertEquals("fizz", result);
    }
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20, 25})
    void shouldReturnBuzzForMultiplesOf5(int number) {
        //when
        String result = fizzBuzz.getNumber(number);
        //then
        assertEquals("buzz", result);
    }
}