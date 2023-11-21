package com.blueskiron.trading.model;

sealed interface OrderBookEntry permits Ask, Bid {

  Side side();

  double amount();

  double price();
}
