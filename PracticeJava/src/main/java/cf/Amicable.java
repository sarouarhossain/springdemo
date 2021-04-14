package cf;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Amicable {
  class Data {
    Integer num;
    Integer sumDiv;

    public Data(Integer num, Integer sumDiv) {
      this.num = num;
      this.sumDiv = sumDiv;
    }
  }

  CompletableFuture<Data> getSumDivs(Integer num) {
    return CompletableFuture.supplyAsync(
        () -> {
          Integer sum = 0;
          for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
              sum += i;
            }
          }
          return new Data(num, sum);
        });
  }

  public void printAmicable(Integer limit) {

    var futures =
        IntStream.range(2, limit)
            .boxed()
            .map(
                num -> {
                  return getSumDivs(num);
                })
            .collect(Collectors.toList());

    var allFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

    var allData =
        allFuture
            .thenApplyAsync(
                v -> {
                  return futures.stream().map(f -> f.join()).collect(Collectors.toList());
                })
            .join();

    Map<Integer, Integer> mapDiv = new HashMap<>();

    allData.forEach(
        data -> {
          if (data.sumDiv != 1) mapDiv.put(data.num, data.sumDiv);
        });

    for (int i = 2; i <= limit; i++) {
      var x = mapDiv.get(i);
      var y = mapDiv.get(x);

      if (y != null && i == y) {
        System.out.println(i + " and " + x + " are amicable");
      }
    }
  }

  public static void main(String[] args) {
    Amicable amicable = new Amicable();
    amicable.printAmicable(999999);
  }
}
