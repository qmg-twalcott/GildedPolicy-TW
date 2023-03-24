package org.example;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Capturing Gilded Policy Requirements")
class GildedPolicyTest {

  @Nested
  @DisplayName("Bronze Policy Test")
  class BronzePolicyTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void bronzePercentageIncreasesBy3IfExpiryDateIsLessThan5(int expiryIn) {
      Policy[] policies =
          new Policy[] {new Policy("Bronze package policy (cheapest policy)", expiryIn, 45)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(48));
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 7, 8, 9, 10})
    void bronzePercentageIncreasesBy32IfExpiryDateIsLessThan10(int expiryIn) {
      Policy[] policies =
          new Policy[] {new Policy("Bronze package policy (cheapest policy)", expiryIn, 45)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(47));
    }

    @Test
    void bronzePercentageDropsToZeroAfterExpiryDateHasPassed() {
      Policy[] policies =
          new Policy[] {new Policy("Bronze package policy (cheapest policy)", 0, 45)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(0));
    }

    @Test
    void bronzePercentageIncreasesBy1IfExpiryDateIsGreaterThan10() {
      Policy[] policies =
          new Policy[] {new Policy("Bronze package policy (cheapest policy)", 11, 45)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(46));
    }

    @ParameterizedTest
    @CsvSource({
      "49,50", "50,50",
    })
    void bronzePercentageShouldNeverBeMoreThan50WhenExpiryIsLessThan11(
        int startPercentage, int expectedPercentage) {
      Policy[] policies =
          new Policy[] {new Policy("Bronze package policy (cheapest policy)", 10, startPercentage)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(expectedPercentage));
    }

    @ParameterizedTest
    @CsvSource({
      "49,50", "50,50",
    })
    void bronzePercentageShouldNeverBeMoreThan50WhenExpiryIsLessThan6(
        int startPercentage, int expectedPercentage) {
      Policy[] policies =
          new Policy[] {new Policy("Bronze package policy (cheapest policy)", 5, startPercentage)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(expectedPercentage));
    }
  }

  @Nested
  @DisplayName("Silver Policy Test")
  class SilverPolicyTest {
    @Test
    void silverPercentageIncreasesTwiceAsFastAfterExpiryDateHasPassed() {
      Policy[] policies = new Policy[] {new Policy("Silver Policy", 0, 45)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(47));
    }

    @Test
    void silverPercentageIncreasesBy1AsItAgesBeforeTheExpiryDate() {
      Policy[] policies = new Policy[] {new Policy("Silver Policy", 5, 45)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(46));
    }

    @ParameterizedTest
    @CsvSource({
      "49,50", "50,50",
    })
    void percentageShouldNeverBeMoreThan50(int startPercentage, int expectedPercentage) {
      Policy[] policies = new Policy[] {new Policy("Silver Policy", 11, startPercentage)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(expectedPercentage));
    }
  }

  @Nested
  @DisplayName("Gold Policy Test")
  class GoldPolicyTest {

    @Test
    void goldPercentageAndExpiryInShouldNeverDecrease() {
      Policy[] policies =
          new Policy[] {new Policy("Gold, the very best for the finest people", 5, 45)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(45));
      assertThat(app.policies[0].expiryIn, equalTo(5));
    }
  }

  @Nested
  @DisplayName("General Policy Test")
  class GeneralPolicyTest {
    @Test
    void shouldReturnPolicyName() {
      String policyName = "Policy Name";
      Policy[] policies = new Policy[] {new Policy(policyName, 0, 0)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(policyName, equalTo(app.policies[0].name));
    }

    @Test
    void percentageCannotBeNegative() {
      Policy[] policies = new Policy[] {new Policy("Policy Name", 0, 0)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(0));
    }

    @Test
    void percentageDecreasesTwiceAsFastOnceExpiryDateHasPasses() {
      Policy[] policies = new Policy[] {new Policy("Policy Name", -1, 45)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(43));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 4, 3, 2, 1, 0})
    void expiryDateDecreasesBy1WithEveryUpdate(int expiryIn) {
      Policy[] policies = new Policy[] {new Policy("Policy Name", expiryIn, 45)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].expiryIn, equalTo(expiryIn - 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 9, 8, 7, 6})
    void percentageDropsBy1WithEachUpdate(int percentage) {
      Policy[] policies = new Policy[] {new Policy("Policy Name", 7, percentage)};
      GildedPolicy app = new GildedPolicy(policies);

      app.update();

      assertThat(app.policies[0].percentage, equalTo(percentage - 1));
    }
  }
}
