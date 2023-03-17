package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedPolicyTest {

    @Test
    void shouldReturnPolicyName() {
        String policyName = "Policy Name";
        Policy[] policies = new Policy[]{new Policy(policyName, 0, 0)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(policyName, app.policies[0].name);
    }

    @Test
    void percentageCannotBeNegative() {
        Policy[] policies = new Policy[]{new Policy("Policy Name", 0, 0)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(app.policies[0].percentage, 0);
    }

    @Test
    void percentageShouldNeverBeMoreThan50() {
        Policy[] policies = new Policy[]{new Policy("Silver Policy", 0, 50)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(app.policies[0].percentage, 50);
    }

    @Test
    void silverPercentageIncreasesTwiceAsFastAfterExpiryDateHasPassed() {
        Policy[] policies = new Policy[]{new Policy("Silver Policy", 0, 45)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(app.policies[0].percentage, 47);
    }

    @Test
    void silverPercentageIncreasesBy1AsItAgesBeforeTheExpiryDate() {
        Policy[] policies = new Policy[]{new Policy("Silver Policy", 5, 45)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(app.policies[0].percentage, 46);
    }

    @Test
    void goldPercentageAndExpiryInShouldNeverDecrease() {
        Policy[] policies = new Policy[]{new Policy("Gold, the very best for the finest people", 5, 45)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(app.policies[0].percentage, 45);
        assertEquals(app.policies[0].expiryIn, 5);
    }

    @Test
    void percentageDecreasesTwiceAsFastOnceExpiryDateHasPasses() {
        Policy[] policies = new Policy[]{new Policy("Policy Name", -1, 45)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(app.policies[0].percentage, 43);
    }

    @Test
    void bronzePercentageIncreasesBy1IfExpiryDateIsGreaterThan10() {
        Policy[] policies = new Policy[]{new Policy("Bronze package policy (cheapest policy)", 11, 45)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(app.policies[0].percentage, 46);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void bronzePercentageIncreasesBy3IfExpiryDateIsLessThan5(int expiryIn) {
        Policy[] policies = new Policy[]{new Policy("Bronze package policy (cheapest policy)", expiryIn, 45)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(app.policies[0].percentage, 48);
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 7, 8, 9, 10})
    void bronzePercentageIncreasesBy32IfExpiryDateIsLessThan10(int expiryIn) {
        Policy[] policies = new Policy[]{new Policy("Bronze package policy (cheapest policy)", expiryIn, 45)};
        GildedPolicy app = new GildedPolicy(policies);

        app.updatePercentage();

        assertEquals(app.policies[0].percentage, 47);
    }
}