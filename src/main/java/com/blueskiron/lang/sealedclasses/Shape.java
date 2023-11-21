package com.blueskiron.lang.sealedclasses;

abstract sealed class Shape permits Circle, Rectangle, Square {
  private final int nrOfSides;

  public Shape(int nrOfSides) {
    this.nrOfSides = nrOfSides;
  }

  public int getNrOfSides() {
    return nrOfSides;
  }
}
