package com.blueskiron.trading.model;

/**
 * immutable record representing tradeble asset
 *
 * @param code
 * @param precision
 */
public record FxCurrency(String code, int precision) implements Asset {
  public static final int DEFAULT_PRECISION = 2;
  public static FxCurrency EUR = new FxCurrency("EUR", DEFAULT_PRECISION);
  public static FxCurrency USD = new FxCurrency("USD", DEFAULT_PRECISION);
  public static FxCurrency GBP = new FxCurrency("GBP", DEFAULT_PRECISION);

  public static FxCurrency of(String code) {
    return new FxCurrency(code, DEFAULT_PRECISION);
  }

  @Override
  public String toString() {
    return code;
  }
}
