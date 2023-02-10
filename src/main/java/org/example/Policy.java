package org.example;

public class Policy {

    public String name;

    public int expiryIn;

    public int percentage;

    public Policy(String name, int expiryIn, int percentage) {
        this.name = name;
        this.expiryIn = expiryIn;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.expiryIn + ", " + this.percentage;
    }
}
