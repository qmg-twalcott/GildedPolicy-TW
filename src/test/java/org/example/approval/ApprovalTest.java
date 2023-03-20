package org.example.approval;

import java.util.stream.IntStream;
import org.approvaltests.combinations.CombinationApprovals;
import org.example.GildedPolicy;
import org.example.Policy;
import org.junit.jupiter.api.Test;

class ApprovalTest {

  @Test
  void shouldCaptureOutputOfSystemAcrossRangeOfInputs() throws Exception {
    Integer[] lotsOfNumbers = IntStream.range(-100, 100).boxed().toArray(Integer[]::new);

    CombinationApprovals.verifyAllCombinations(
        this::callUpdate,
        new String[] {
          "a common policy",
          "Silver Policy",
          "Bronze package policy (cheapest policy)",
          "Gold, the very best for the finest people"
        },
        lotsOfNumbers,
        lotsOfNumbers);
  }

  private String callUpdate(String name, int sellIn, int quality) {
    var items = new Policy[] {new Policy(name, sellIn, quality)};
    var gildedPolicy = new GildedPolicy(items);

    gildedPolicy.update();

    return gildedPolicy.policies[0].toString();
  }
}
