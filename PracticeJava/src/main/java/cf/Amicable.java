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
          Integer sum = 1;
          for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
              sum += i;
              sum += num / i;
            }
          }
          return new Data(num, sum);
        });
  }

  public void printAmicable(Integer limit) {

    var futures =
        IntStream.range(2, limit)
            .boxed()
            .parallel()
            .map(this::getSumDivs)
            .collect(Collectors.toList());

    var allFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

    var allData =
        allFuture
            .thenApplyAsync(
                v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
            .join();

    Map<Integer, Integer> mapDiv = new HashMap<>();

    allData.forEach(
        data -> {
          if (data.sumDiv != 1) mapDiv.put(data.num, data.sumDiv);
        });

    int count = 0;
    for (int i = 2; i <= limit; i++) {
      var x = mapDiv.get(i);
      var y = mapDiv.get(x);

      if (y != null && i == y && i <= x) {
        System.out.println(i + " and " + x + " are amicable");
        count++;
      }
    }

    System.out.println("Count : " + count);
  }

  public static void main(String[] args) {
    Amicable amicable = new Amicable();
    amicable.printAmicable(999999);
  }
}
