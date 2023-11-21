package com.blueskiron.lang.localvar;

import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class Example {

  public static Long sumOfIntRanges(Integer a, Integer b) {
    BiFunction<Integer, Integer, Long> func = (var x, var y) ->
        Long.valueOf(IntStream.rangeClosed(1, x).sum()) + Long.valueOf(IntStream.rangeClosed(1, y).sum());
    return func.apply(a, b);
  }

  public static void main(String[] args) {
    final var upperA = 1000000;
    final var upperB = upperA;
    final var res = sumOfIntRanges(upperA, upperB);
    System.out.println(String.format("sum of (1..%d) and (1..%d) = %d", upperA, upperB, res));
  }
}
