package com.example.ReactiveTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/async/")
public class AsyncTestController {
  private static final String API_BASE = "http://localhost:9090/api/v1/mock/";

  @GetMapping("/naive")
  public List<Integer> naiveSolution(@RequestParam(value = "key") List<String> keys)
      throws URISyntaxException, IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    List<Integer> dataList = new ArrayList<>();
    for (String key : keys) {
      var res =
          client
              .send(
                  HttpRequest.newBuilder(new URI(API_BASE + key)).GET().build(),
                  HttpResponse.BodyHandlers.ofString())
              .body();
      dataList.add(Integer.parseInt(res));
    }
    return dataList;
  }

  @GetMapping("/usingCf")
  public CompletionStage<List<Integer>> asyncSolution(
      @RequestParam(value = "key") List<String> keys) throws URISyntaxException {
    HttpClient client = HttpClient.newHttpClient();
    List<URI> targets = new ArrayList<>();
    for (String key : keys) {
      targets.add(new URI(API_BASE + key));
    }
    List<CompletableFuture<Integer>> futures =
        targets.stream()
            .map(
                target ->
                    client
                        .sendAsync(
                            HttpRequest.newBuilder(target).GET().build(),
                            HttpResponse.BodyHandlers.ofString())
                        .thenApplyAsync(response -> Integer.parseInt(response.body())))
            .collect(Collectors.toList());

    var allFutures =
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

    return allFutures.thenApplyAsync(
        v -> {
          return futures.stream().map(future -> future.join()).collect(Collectors.toList());
        });
  }

  @GetMapping("/usingf")
  public List<Integer> asyncSolutionOld(@RequestParam(value = "key") List<String> keys)
      throws InterruptedException {
    List<Callable<String>> callableTask = new ArrayList<>();
    HttpClient client = HttpClient.newHttpClient();
    for (String key : keys) {
      Callable<String> callable =
          () -> {
            var res =
                client.send(
                    HttpRequest.newBuilder(new URI(API_BASE + key)).GET().build(),
                    HttpResponse.BodyHandlers.ofString());
            return res.body();
          };
      callableTask.add(callable);
    }

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    var futures = executorService.invokeAll(callableTask);

    List<Integer> dataList =
        futures.parallelStream()
            .map(
                future -> {
                  try {
                    return Integer.parseInt(future.get());
                  } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return 0;
                  }
                })
            .collect(Collectors.toList());
    return dataList;
  }

  @GetMapping("/usingReactive")
  public Flux<Integer> asyncSolutionReactive(@RequestParam(value = "key") List<String> keys) {
    WebClient webClient = WebClient.create();
    Scheduler scheduler = Schedulers.newParallel("test", 10);

    return Flux.fromIterable(keys)
        .publishOn(scheduler)
        .flatMap(
            key ->
                webClient
                    .get()
                    .uri(API_BASE + key)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(Integer::parseInt));
  }
}
