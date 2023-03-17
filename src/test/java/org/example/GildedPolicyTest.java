package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import static org.junit.jupiter.api.Assertions.*;

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

}