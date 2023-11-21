package com.blueskiron.trading.model;

public record Bid(double price, double amount) implements OrderBookEntry {

  @Override
  public Side side() {
    return Side.BUY;
  }
}
