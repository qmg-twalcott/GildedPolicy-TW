package org.example;

import static org.example.PolicyNames.*;

public class GildedPolicy {
  public Policy[] policies;

  public GildedPolicy(Policy[] policies) {
    this.policies = policies;
  }

  public void update() {
    for (int i = 0; i < policies.length; i++) {
      if (!policies[i].name.equals(SILVER.policyName)
          && !policies[i].name.equals(BRONZE.policyName)) {
        if (policies[i].percentage > 0) {
          if (!policies[i].name.equals(GOLD.policyName)) {
            policies[i].percentage = policies[i].percentage - 1;
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

      if (!policies[i].name.equals(GOLD.policyName)) {
        policies[i].expiryIn = policies[i].expiryIn - 1;
      }

      if (policies[i].expiryIn < 0) {
        if (!policies[i].name.equals(SILVER.policyName)) {
          if (!policies[i].name.equals(BRONZE.policyName)) {
            if (policies[i].percentage > 0) {
              if (!policies[i].name.equals(GOLD.policyName)) {
                policies[i].percentage = policies[i].percentage - 1;
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
}
