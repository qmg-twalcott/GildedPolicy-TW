package org.example;

import org.junit.jupiter.api.Test;

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

}