package com.blueskiron.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class TreeMapBenchMark {
  private static final int COLLECTION_SIZE = 50 * 1000;
  private final Random rand = new Random();
  private final List<Tuple2<Long, Object>> values = new ArrayList<>();
  private final TreeMap<Long, Object> vanilla = new TreeMap<>((k1, k2) -> k1.compareTo(k2));
  private final io.vavr.collection.TreeMap<Long, Object> vavr =
      io.vavr.collection.TreeMap.empty((k1, k2) -> k1.compareTo(k2));

  @Setup
  public void setup() {
    IntStream.rangeClosed(1, COLLECTION_SIZE).forEach(iter -> values.add(new Tuple2<>(rand.nextLong(), new Object())));
  }

  @Benchmark
  @Fork(value = 1, warmups = 2)
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Warmup(iterations = 5, time = 1)
  @Measurement(iterations = 10, time = 1)
  public void vanilla() {
    for (Tuple2<Long, Object> v : values) {
      vanilla.put(v.get_1(), v.get_2());
    }
  }

  @Benchmark
  @Fork(value = 1, warmups = 2)
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Warmup(iterations = 5, time = 1)
  @Measurement(iterations = 10, time = 1)
  public void vavr() {
    for (Tuple2<Long, Object> v : values) {
      vavr.put(v.get_1(), v.get_2());
    }
  }

  public static void main(String[] args) throws IOException {
    org.openjdk.jmh.Main.main(args);
  }

  private static class Tuple2<T1, T2> {
    private final T1 t1;
    private final T2 t2;

    Tuple2(T1 t1, T2 t2) {
      this.t1 = t1;
      this.t2 = t2;
    }

    public T1 get_1() {
      return t1;
    }

    public T2 get_2() {
      return t2;
    }
  }
}
