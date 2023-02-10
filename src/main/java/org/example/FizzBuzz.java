package org.example;

public class FizzBuzz {

    public String getNumber(int number) {
        if (isaMultipleOf3(number)) return "fizz";
        if (isaMultipleOf5(number)) return "buzz";
        return Integer.toString(number);
    }

    private static boolean isaMultipleOf5(int number) {
        return number % 5 == 0;
    }

    private static boolean isaMultipleOf3(int number) {
        return number % 3 == 0;
    }
}
