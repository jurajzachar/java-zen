package com.blueskiron.trading.model;

public sealed interface Asset permits FxCurrency {
  /**
   * @return unique representation of this asset
   */
  String code();
}
