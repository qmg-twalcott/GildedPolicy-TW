package org.example;

public class TexttestFixture {
    public static void main(String[] args) {
        Policy[] policies = new Policy[]{
                new Policy("Legacy V1 Policy", 10, 20),
                new Policy("Silver Policy", 2, 0),
                new Policy("Meerkat Movies Policy", 5, 7),
                new Policy("Gold, the very best for the finest people", 0, 80),
                new Policy("Gold, the very best for the finest people", -1, 80),
                new Policy("Bronze package policy (cheapest policy)", 15, 20),
                new Policy("Bronze package policy (cheapest policy)", 10, 49),
                new Policy("Bronze package policy (cheapest policy)", 5, 49),
                // this Amazon policy does not work properly yet
                new Policy("Amazon Prime Policy", 3, 6)};

        GildedPolicy app = new GildedPolicy(policies);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, expiryIn, percentage");
            for (Policy item : policies) {
                System.out.println(item);
            }
            System.out.println();
            app.update();
        }
    }

}
