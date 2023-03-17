package org.example;

import org.junit.jupiter.api.Test;

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

}