package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GildedPolicyTest {

    @Test
    void foo() {
        Policy[] policies = new Policy[]{new Policy("foo", 0, 0)};
        GildedPolicy app = new GildedPolicy(policies);
        app.updatePercentage();
        assertEquals("fixme", app.policies[0].name);
    }

}