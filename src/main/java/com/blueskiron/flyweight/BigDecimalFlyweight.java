package com.blueskiron.flyweight;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Apply a simple flyweight pattern on BigDecimal object instantiation from plain strings.
 * This is useful when values are known beforehand in a well-defined range and are repeating in high frequency while
 * marshalling and de-marshalling from textual representation, and the eden space is a concern for GC pauses.
 */
public class BigDecimalFlyweight {
  public static final long DEFAULT_VAL_RANGE_FROM = 1L;
  public static final long DEFAULT_VAL_RANGE_TO = Double.valueOf(Math.pow(10, 7)).longValue();
  public static final int DEFAULT_VAL_SCALE = 5;

  private final long valRangeFrom;

  private final long valRangeTo;
  /* a BigDecimal whose value is (unscaledVal × 10-scale).*/
  private final int valScale;

  private final Map<String, BigDecimal> cache = new HashMap<>();

  public BigDecimalFlyweight(long valRangeFrom, long valRangeTo, int valScale) {
    this.valRangeFrom = valRangeFrom;
    this.valRangeTo = valRangeTo;
    this.valScale = valScale;
  }

  public static BigDecimalFlyweight withDefaults() {
    // generates 1 trillion objects
    return new BigDecimalFlyweight(DEFAULT_VAL_RANGE_FROM, DEFAULT_VAL_RANGE_TO, DEFAULT_VAL_SCALE);
  }

  public BigDecimalFlyweight initiate() {
    LongStream.rangeClosed(this.valRangeFrom, this.valRangeTo)
        .mapToObj(
            value -> IntStream.rangeClosed(1, this.valScale).mapToObj(scale -> BigDecimal.valueOf(value, scale)))
        .flatMap(Function.identity())
        .forEach(entry -> cache.put(entry.toPlainString(), entry));

    return this;
  }

  public int size() {
    return cache.size();
  }

  public BigDecimal get(String plainString) {
    return cache.get(plainString);
  }
}
