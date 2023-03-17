package org.example;

import static org.example.FizzBuzz.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FizzBuzzTest {

  private final FizzBuzz fizzBuzz = new FizzBuzz();

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 4, 7, 8})
  void shouldReturnNumberAsAString(int number) {
    // when
    String result = fizzBuzz.getNumber(number);
    // then
    assertEquals(Integer.toString(number), result);
  }

  @ParameterizedTest
  @ValueSource(ints = {3, 6, 9, 12})
  void shouldReturnFizzForMultiplesOf3(int number) {
    // when
    String result = fizzBuzz.getNumber(number);
    // then
    assertEquals(FIZZ, result);
  }

  @ParameterizedTest
  @ValueSource(ints = {5, 10, 20, 25})
  void shouldReturnBuzzForMultiplesOf5(int number) {
    // when
    String result = fizzBuzz.getNumber(number);
    // then
    assertEquals(BUZZ, result);
  }

  @ParameterizedTest
  @ValueSource(ints = {15, 30, 60})
  void shouldReturnFizzBuzzForMultipleOf3And5(int number) {
    // when
    String result = fizzBuzz.getNumber(number);
    // then
    assertEquals(FIZZ_BUZZ, result);
  }
}
