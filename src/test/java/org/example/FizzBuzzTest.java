package org.example;

import org.junit.jupiter.api.Test;

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

   @Test
    void shouldReturnFizzForMultipleOf3() {
       FizzBuzz fizzBuzz = new FizzBuzz();
       //when
       String result = fizzBuzz.getNumber(3);
       //then
       assertEquals("fizz", result);
   }
}