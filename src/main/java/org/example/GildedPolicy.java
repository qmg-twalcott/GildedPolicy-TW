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
        if (policies[i].percentage < 50) {
          policies[i].percentage = policies[i].percentage + 1;

          if (policies[i].name.equals(BRONZE.policyName)) {
            if (policies[i].expiryIn < 11) {
              if (policies[i].percentage < 50) {
                policies[i].percentage = policies[i].percentage + 1;
              }
            }

            if (policies[i].expiryIn < 6) {
              if (policies[i].percentage < 50) {
                policies[i].percentage = policies[i].percentage + 1;
              }
            }
          }
        }
      }

      if (policyIsNotGold(policies[i])) {
        policies[i].expiryIn = policies[i].expiryIn - 1;
      }

      if (policies[i].expiryIn < 0) {
        if (!policies[i].name.equals(SILVER.policyName)) {
          if (!policies[i].name.equals(BRONZE.policyName)) {
            if (percentageIsMoreThanZero(policies[i])) {
              if (policyIsNotGold(policies[i])) {
                decreasePercentageBy1(policies[i]);
              }
            }
          } else {
            policies[i].percentage = policies[i].percentage - policies[i].percentage;
          }
        } else {
          if (policies[i].percentage < 50) {
            policies[i].percentage = policies[i].percentage + 1;
          }
        }
      }
    }
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
    return !policy.name.equals(SILVER.policyName) && !policy.name.equals(BRONZE.policyName);
  }
}
