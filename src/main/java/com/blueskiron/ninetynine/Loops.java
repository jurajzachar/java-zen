package com.blueskiron.ninetynine;

import java.util.IntSummaryStatistics;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Loops {
  //2.1 Write a method that prints the numbers 1 to 10
  public static void oneToTen() {
    IntStream.rangeClosed(1, 10).forEach(num -> System.out.println(num));
  }

  //2.2 Write a method that prints the positive odd numbers less than 20
  public static void oddNumbers() {
    IntStream.rangeClosed(1, 20).filter(num -> num % 2 == 1).forEach(num -> System.out.println(num));
  }

  //2.3 Square numbers
  public static void squares() {
    IntStream.rangeClosed(1, 10).map(num -> num * num).forEach(num -> System.out.println(num));
  }

  public static void powers(int upperBound) {
    IntStream.rangeClosed(1, upperBound)
        .mapToDouble(num -> Math.pow(2, num))
        .mapToInt(num -> Double.valueOf(num).intValue())
        .forEach(num -> System.out.println(num));
  }
}
