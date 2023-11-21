package com.blueskiron.trading.model;

public record Ask(double price, double amount) implements OrderBookEntry {
  @Override
  public Side side() {
    return Side.SELL;
  }

}
