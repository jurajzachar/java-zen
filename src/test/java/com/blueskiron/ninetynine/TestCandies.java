package com.blueskiron.ninetynine;

import com.blueskiron.leetcode.Candy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCandies {

  @Test
  void shouldPassFirstScenario() {
    int[] ratings = {1, 0, 2};
    Assertions.assertEquals(5, Candy.candy(ratings));
  }

  @Test
  void shouldPassSecondScenario() {
    int[] ratings = {1, 2, 2};
    Assertions.assertEquals(4, Candy.candy(ratings));
  }
  @Test
  void shouldPassThirdScenario() {
    int[] ratings = {2, 2, 1};
    Assertions.assertEquals(4, Candy.candy(ratings));
  }
}
