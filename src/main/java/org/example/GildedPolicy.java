package org.example;

import static org.example.PolicyNames.*;

public class GildedPolicy {
  public Policy[] policies;

  public GildedPolicy(Policy[] policies) {
    this.policies = policies;
  }

  public void update() {
    for (int i = 0; i < policies.length; i++) {
      if (isNotSilverOrBronze(policies[i])) {
        if (percentageIsMoreThanZero(policies[i])) {
          if (policyIsNotGold(policies[i])) {
            decreasePercentageBy1(policies[i]);
          }
        }
      } else {
        if (percentageLessThan50(i)) {
          increasePercentageBy1(policies[i]);

          if (ifPolicyIsBronze(i)) {
            if (policyExpiryInLessThan(11, i)) {
              if (percentageLessThan50(i)) {
                increasePercentageBy1(policies[i]);
              }
            }

            if (policyExpiryInLessThan(6, i)) {
              if (percentageLessThan50(i)) {
                increasePercentageBy1(policies[i]);
              }
            }
          }
        }
      }

      if (policyIsNotGold(policies[i])) {
        policies[i].expiryIn = policies[i].expiryIn - 1;
      }

      if (policyExpiryInLessThan(0, i)) {
        if (policyIsNotSilver(policies[i])) {
          if (!ifPolicyIsBronze(i)) {
            if (percentageIsMoreThanZero(policies[i])) {
              if (policyIsNotGold(policies[i])) {
                decreasePercentageBy1(policies[i]);
              }
            }
          } else {
            policies[i].percentage = 0;
          }
        } else {
          if (percentageLessThan50(i)) {
            increasePercentageBy1(policies[i]);
          }
        }
      }
    }
  }

  private boolean policyIsNotSilver(Policy policy) {
    return !policy.name.equals(SILVER.policyName);
  }

  private boolean policyExpiryInLessThan(int expiryIn, int i) {
    return policies[i].expiryIn < expiryIn;
  }

  private boolean ifPolicyIsBronze(int i) {
    return policies[i].name.equals(BRONZE.policyName);
  }

  private boolean percentageLessThan50(int i) {
    return policies[i].percentage < 50;
  }

  private void increasePercentageBy1(Policy policy) {
    policy.percentage = policy.percentage + 1;
  }

  private void decreasePercentageBy1(Policy policy) {
    policy.percentage = policy.percentage - 1;
  }

  private boolean policyIsNotGold(Policy policy) {
    return !policy.name.equals(GOLD.policyName);
  }

  private boolean percentageIsMoreThanZero(Policy policy) {
    return policy.percentage > 0;
  }

  private boolean isNotSilverOrBronze(Policy policy) {
    return policyIsNotSilver(policy) && !policy.name.equals(BRONZE.policyName);
  }
}
