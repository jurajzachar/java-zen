package com.blueskiron.leetcode;

import java.util.Arrays;

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * <p>
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 * <p>
 * Example 1:
 * <p>
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * <p>
 * Example 2:
 * <p>
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 * <p>
 * Example 3:
 * Input: ratings = [2,2,1]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 1, 2 candies respectively.
 * The second child gets 1 candy because it satisfies the above two conditions.
 */
public class Candy {
  public static int candy(int[] ratings) {
    int max = ratings.length;
    int[] tally = new int[max];
    for (int i = 0; i < max; i++) {
      int currentRating = ratings[i];
      // check bonus condition with the next neighbour
      //has next?
      if (i + 1 < max) {
        int nextRating = ratings[i + 1];
        if (currentRating > nextRating) {
          tally[i] = 2; //base + 1;
          continue;
        }
      }
      //has previous?
      if (i - 1 >= 0) {
        int previousRating = ratings[i - 1];
        int previousCandies = tally[i - 1];
        // bonus condition
        if (currentRating > previousRating) {
          tally[i] = previousCandies + 1;
          continue;
        }
      }
      //base case: each child gets at least one candy
      tally[i] = 1;
    }
    return Arrays.stream(tally).sum();
  }
}
