package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Capturing Gilded Policy Requirements")
class GildedPolicyTest {
    @Nested
    @DisplayName("Bronze Policy Test")
    class BronzePolicyTest {
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5})
        void bronzePercentageIncreasesBy3IfExpiryDateIsLessThan5(int expiryIn) {
            Policy[] policies = new Policy[]{new Policy("Bronze package policy (cheapest policy)", expiryIn, 45)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 48);
        }

        @ParameterizedTest
        @ValueSource(ints = {6, 7, 8, 9, 10})
        void bronzePercentageIncreasesBy32IfExpiryDateIsLessThan10(int expiryIn) {
            Policy[] policies = new Policy[]{new Policy("Bronze package policy (cheapest policy)", expiryIn, 45)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 47);
        }

        @Test
        void bronzePercentageDropsToZeroAfterExpiryDateHasPassed() {
            Policy[] policies = new Policy[]{new Policy("Bronze package policy (cheapest policy)", 0, 45)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 0);
        }

        @Test
        void bronzePercentageIncreasesBy1IfExpiryDateIsGreaterThan10() {
            Policy[] policies = new Policy[]{new Policy("Bronze package policy (cheapest policy)", 11, 45)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 46);
        }

    }

    @Nested
    @DisplayName("Silver Policy Test")
    class SilverPolicyTest {
        @Test
        void silverPercentageIncreasesTwiceAsFastAfterExpiryDateHasPassed() {
            Policy[] policies = new Policy[]{new Policy("Silver Policy", 0, 45)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 47);
        }

        @Test
        void silverPercentageIncreasesBy1AsItAgesBeforeTheExpiryDate() {
            Policy[] policies = new Policy[]{new Policy("Silver Policy", 5, 45)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 46);
        }
    }

    @Nested
    @DisplayName("Gold Policy Test")
    class GoldPolicyTest{

        @Test
        void goldPercentageAndExpiryInShouldNeverDecrease() {
            Policy[] policies = new Policy[]{new Policy("Gold, the very best for the finest people", 5, 45)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 45);
            assertEquals(app.policies[0].expiryIn, 5);
        }

    }

    @Nested
    @DisplayName("General Policy Test")
    class GeneralPolicyTest {
        @Test
        void shouldReturnPolicyName() {
            String policyName = "Policy Name";
            Policy[] policies = new Policy[]{new Policy(policyName, 0, 0)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(policyName, app.policies[0].name);
        }

        @Test
        void percentageCannotBeNegative() {
            Policy[] policies = new Policy[]{new Policy("Policy Name", 0, 0)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 0);
        }

        @Test
        void percentageShouldNeverBeMoreThan50() {
            Policy[] policies = new Policy[]{new Policy("Silver Policy", 0, 50)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 50);
        }

        @Test
        void percentageDecreasesTwiceAsFastOnceExpiryDateHasPasses() {
            Policy[] policies = new Policy[]{new Policy("Policy Name", -1, 45)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, 43);
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 4, 3, 2, 1, 0})
        void expiryDateDecreasesBy1WithEveryUpdate(int expiryIn) {
            Policy[] policies = new Policy[]{new Policy("Policy Name", expiryIn, 45)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].expiryIn, expiryIn - 1);
        }

        @ParameterizedTest
        @ValueSource(ints = {10, 9, 8, 7, 6})
        void percentageDropsBy1WithEachUpdate(int percentage) {
            Policy[] policies = new Policy[]{new Policy("Policy Name", 7, percentage)};
            GildedPolicy app = new GildedPolicy(policies);

            app.update();

            assertEquals(app.policies[0].percentage, percentage - 1);
        }
    }
}