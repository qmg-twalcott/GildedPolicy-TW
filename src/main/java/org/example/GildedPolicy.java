package org.example;

import static org.example.PolicyNames.*;

public class GildedPolicy {
  public Policy[] policies;

  public GildedPolicy(Policy[] policies) {
    this.policies = policies;
  }

  public void update() {
    for (Policy policy : policies) {

      if (!policyIs(SILVER, policy) && !policyIs(BRONZE, policy)) {
        if (percentageIsMoreThanZero(policy)) {
          if (!policyIs(GOLD, policy)) {
            decreasePercentageBy1(policy);
          }
        }
      } else {
        if (percentageLessThan50(policy)) {
          increasePercentageBy1(policy);

          if (policyIs(BRONZE, policy)) {
            if (policyExpiryInLessThan(11, policy)) {
              if (percentageLessThan50(policy)) {
                increasePercentageBy1(policy);
              }
            }

            if (policyExpiryInLessThan(6, policy)) {
              if (percentageLessThan50(policy)) {
                increasePercentageBy1(policy);
              }
            }
          }
        }
      }

      if (!policyIs(GOLD, policy)) {
        policy.expiryIn = policy.expiryIn - 1;
      }

      if (policyExpiryInLessThan(0, policy)) {
        if (!policyIs(SILVER, policy)) {
          if (!policyIs(BRONZE, policy)) {
            if (percentageIsMoreThanZero(policy)) {
              if (!policyIs(GOLD, policy)) {
                decreasePercentageBy1(policy);
              }
            }
          } else {
            policy.percentage = 0;
          }
        } else {
          if (percentageLessThan50(policy)) {
            increasePercentageBy1(policy);
          }
        }
      }
    }
  }

  private boolean policyIs(PolicyNames policyNames, Policy policy) {
    return policy.name.equals(policyNames.policyName);
  }

  private boolean policyExpiryInLessThan(int expiryIn, Policy policy) {
    return policy.expiryIn < expiryIn;
  }

  private boolean percentageLessThan50(Policy policy) {
    return policy.percentage < 50;
  }

  private void increasePercentageBy1(Policy policy) {
    policy.percentage = policy.percentage + 1;
  }

  private void decreasePercentageBy1(Policy policy) {
    policy.percentage = policy.percentage - 1;
  }

  private boolean percentageIsMoreThanZero(Policy policy) {
    return policy.percentage > 0;
  }
}
