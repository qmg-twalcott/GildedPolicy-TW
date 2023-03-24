package org.example;

public enum PolicyNames {
  SILVER("Silver Policy"),

  BRONZE("Bronze package policy (cheapest policy)"),

  GOLD("Gold, the very best for the finest people");

  public final String policyName;

  private PolicyNames(String policyName) {
    this.policyName = policyName;
  }
}
