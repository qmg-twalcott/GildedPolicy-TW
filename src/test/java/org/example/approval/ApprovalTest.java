package org.example.approval;

import static java.util.stream.IntStream.range;

import org.approvaltests.combinations.CombinationApprovals;
import org.example.GildedPolicy;
import org.example.Policy;
import org.junit.jupiter.api.Test;

class ApprovalTest {

  @Test
  void shouldCaptureOutputOfSystemAcrossRangeOfInputs() throws Exception {

    CombinationApprovals.verifyAllCombinations(
        this::callUpdate,
        new String[] {
          "a common policy",
          "Silver Policy",
          "Bronze package policy (cheapest policy)",
          "Gold, the very best for the finest people"
        },
        range(-100, 100).boxed().toArray(Integer[]::new),
        range(-100, 100).boxed().toArray(Integer[]::new));
  }

  private String callUpdate(String name, int expiryIn, int percentage) {
    var items = new Policy[] {new Policy(name, expiryIn, percentage)};
    var gildedPolicy = new GildedPolicy(items);

    gildedPolicy.update();

    return gildedPolicy.policies[0].toString();
  }
}
