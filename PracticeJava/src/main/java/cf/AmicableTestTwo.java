package cf;

import java.util.Arrays;

public class AmicableTestTwo {
  public static void main(String[] args) {
    //
    int limit = 100000000;
    int[] sum = new int[limit + 2];

    for (int i = 1; i <= limit; i++) {
      for (int j = i + i; j <= limit; j += i) {
        sum[j] += i;
      }
    }

    for (int i = 1; i < limit; i++) {
      var x = sum[i];
      var y = x < sum.length ? sum[x] : -1;

      if (i == y && i <= x) {
        System.out.println(i + " and " + x + " are amicable");
      }
    }
  }
}
