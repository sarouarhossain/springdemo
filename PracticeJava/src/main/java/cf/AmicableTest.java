package cf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmicableTest {
  public static void main(String[] args) {
    //
    Map<Integer, Integer> mapDiv = new HashMap<>();

    Integer limit = 999999;

    for (int i = 2; i <= limit; i++) {
      var sumDiv = sumOfDivisors(i);
      if (sumDiv == 1) continue;
      mapDiv.put(i, sumDiv);
    }

    System.out.println(mapDiv);

    for (int i = 2; i <= limit; i++) {
      var x = mapDiv.get(i);
      var y = mapDiv.get(x);

      if (y != null && i == y) {
        System.out.println(i + " and " + x + " are amicable");
      }
    }
  }

  public static Integer sumOfDivisors(Integer num) {
    Integer sum = 0;
    for (int i = 1; i <= num / 2; i++) {
      if (num % i == 0) {
        sum += i;
      }
    }
    return sum;
  }
}
