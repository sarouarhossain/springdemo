package com.example.ReactiveTest.services;

import com.example.ReactiveTest.models.Amicable;
import com.example.ReactiveTest.models.AmicableData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AmicableService {

  public CompletionStage<List<Amicable>> getAmicableList(Integer limit) {

    var futures =
        IntStream.range(2, limit)
            .boxed()
            .parallel()
            .map(this::getSumDivs)
            .collect(Collectors.toList());

    var allFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

    return allFuture
        .thenApplyAsync(
            v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
        .thenApplyAsync(
            allData -> {
              Map<Integer, Integer> mapDiv = new HashMap<>();

              allData.forEach(
                  data -> {
                    if (data.sumDiv != 1) mapDiv.put(data.num, data.sumDiv);
                  });

              List<Amicable> amicableList = new ArrayList<>();
              for (int i = 2; i <= limit; i++) {
                var x = mapDiv.get(i);
                var y = mapDiv.get(x);

                if (y != null && i == y && i <= x) {
                  System.out.println(i + " and " + x + " are amicable");
                  amicableList.add(new Amicable(i, x));
                }
              }
              return amicableList;
            });
  }

  CompletableFuture<AmicableData> getSumDivs(Integer num) {
    return CompletableFuture.supplyAsync(
        () -> {
          System.out.println(Thread.currentThread().getName());
          Integer sum = 0;
          for (int i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
              sum += i;
              sum += num / i;
            }
          }
          return new AmicableData(num, sum);
        });
  }
}
