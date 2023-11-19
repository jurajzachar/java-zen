package com.blueskiron.flyweight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.blueskiron.flyweight.BigDecimalFlyweight.*;

public class TestBigDecimalFlyweight {

  @Test
  @DisabledIfEnvironmentVariable(named = "SKIP_LARGE_HEAP", matches = "True")
  void shouldInstantiateCacheWithDefaults() {
    var start = System.currentTimeMillis();
    var candidate = BigDecimalFlyweight.withDefaults().initiate();
    var expectedSize = DEFAULT_VAL_RANGE_TO * DEFAULT_VAL_SCALE;
    Assertions.assertEquals(expectedSize, candidate.size());
    var stop = System.currentTimeMillis();
    System.out.println("Instantiating " + expectedSize + " BigDecimal objects took " +
        TimeUnit.MILLISECONDS.toSeconds(stop - start) + " seconds.");
    Arrays.asList("12.34567", "0.12345", "99.99999", "98.76543", "1.01010", "0.00001").forEach(testVal ->
      Assertions.assertEquals(new BigDecimal(testVal), candidate.get(testVal))
    );
  }
}
