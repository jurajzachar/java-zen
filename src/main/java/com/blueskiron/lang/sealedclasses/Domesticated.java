package com.blueskiron.lang.sealedclasses;

public enum Domesticated implements Animal {
  Cat, Dog, Goat, Cow, Pig;

  @Override
  public Animal parent() {
    return this;
  }
}
