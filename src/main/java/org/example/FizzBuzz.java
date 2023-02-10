package org.example;

public class FizzBuzz {

    public static final String FIZZ = "fizz";
    public static final String BUZZ = "buzz";
    public static final String FIZZ_BUZZ = "fizz buzz";

    public String getNumber(int number) {
        if (isaMultipleOf(number, 3) && isaMultipleOf(number, 5)) return FIZZ_BUZZ;
        if (isaMultipleOf(number, 3)) return FIZZ;
        if (isaMultipleOf(number, 5)) return BUZZ;
        return Integer.toString(number);
    }

    private static boolean isaMultipleOf(int number, int modNum) {
        return number % modNum == 0;
    }
}
