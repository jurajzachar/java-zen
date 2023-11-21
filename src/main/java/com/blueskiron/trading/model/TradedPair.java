package com.blueskiron.trading.model;

public record TradedPair(Asset base, Asset quote, int marketPrecision, int amountPrecision) {

  public static TradedPair EUR_USD = TradedPair.withFxCurrencies(FxCurrency.EUR, FxCurrency.USD);
  public static TradedPair EUR_GBP = TradedPair.withFxCurrencies(FxCurrency.EUR, FxCurrency.GBP);
  public static TradedPair GBP_USD = TradedPair.withFxCurrencies(FxCurrency.GBP, FxCurrency.USD);

  public TradedPair {
    if (base.code().equals(quote.code())) {
      throw new IllegalArgumentException(
          String.format("base '%s' asset cannot be equal to the quote asset '%s'", base.code(), quote.code()));
    }
  }

  @Override
  public String toString() {
    return String.format("%s_%s", base.code(), quote.code());
  }

  /**
   * @param base
   * @param quote
   * @param marketPrecision
   * @param amountPrecision
   * @return a new TradedPair for two FxCurrrencies
   */
  public static TradedPair withFxCurrencies(
      FxCurrency base,
      FxCurrency quote,
      int marketPrecision,
      int amountPrecision) {
    return new TradedPair(base, quote, marketPrecision, amountPrecision);
  }

  /**
   * the same as {@link TradedPair#withFxCurrencies(FxCurrency, FxCurrency, int, int)},
   * <p>
   * but with <strong>marketPrecision</strong> = 5 and <strong>amountPrecision</strong> = 0
   *
   * @param base
   * @param quote
   * @return a new TradedPair for two FxCurrrencies
   */
  public static TradedPair withFxCurrencies(
      FxCurrency base,
      FxCurrency quote) {
    return new TradedPair(base, quote, 5, 0);
  }
}
