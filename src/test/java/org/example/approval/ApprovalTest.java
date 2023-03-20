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
        new String[] {"a common policy"},
        range(0, 1).boxed().toArray(Integer[]::new),
        range(0, 1).boxed().toArray(Integer[]::new));
  }

  private String callUpdate(String name, int sellIn, int quality) {
    var items = new Policy[] {new Policy(name, sellIn, quality)};
    var gildedPolicy = new GildedPolicy(items);

    gildedPolicy.update();

    return gildedPolicy.policies[0].toString();
  }
}
