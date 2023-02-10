package org.example;

public class FizzBuzz {

    public String getNumber(int number) {
        if (number % 3 == 0) return "fizz";
        else return Integer.toString(number);
    }
}
