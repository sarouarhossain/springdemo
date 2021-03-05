package threadPractice;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Test {
  public static void main(String[] args) throws URISyntaxException {
    //
    String[] uris = {
      "http://localhost:9090/api/v1/mock/YSHFH2gcbTghka9YCwPc8g==",
      "http://localhost:9090/api/v1/mock/Be4jcg1s2UIM1x97NsrAiw==",
      "http://localhost:9090/api/v1/mock/HoxrBKrehQRLx6d78AofvQ==",
      "http://localhost:9090/api/v1/mock/mM1JUCIcB7V5EQdLO0qM5A==",
      "http://localhost:9090/api/v1/mock/tKSsrztNKhWTHdYIxPK8fQ==",
      "http://localhost:9090/api/v1/mock/kZnHt9fPwJuvoVWanA0cFg==",
      "http://localhost:9090/api/v1/mock/jsbVK8r0HOLqn8PRDJb0kQ==",
      "http://localhost:9090/api/v1/mock/wOeIdmthQWc3Brnfhp+5ng==",
      "http://localhost:9090/api/v1/mock/sp4CjEkCPWzDjdm9bwxDug==",
      "http://localhost:9090/api/v1/mock/aa2tvZRyGgjL7yTIOJ0E4g=="
    };

    Instant startTime = Instant.now();

    List<URI> targets = new ArrayList<>();
    for (String uri : uris) {
      targets.add(new URI(uri));
    }

    HttpClient client = HttpClient.newHttpClient();

    List<CompletableFuture<Integer>> futures =
        targets.stream()
            .map(
                target ->
                    client
                        .sendAsync(
                            HttpRequest.newBuilder(target).GET().build(),
                            HttpResponse.BodyHandlers.ofString())
                        .thenApplyAsync(
                            response -> Integer.parseInt(response.body()), getExecutor()))
            .collect(Collectors.toList());

    var allFutures =
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

    var resultFutures =
        allFutures.thenApplyAsync(
            v -> {
              return futures.stream().map(future -> future.join()).collect(Collectors.toList());
            },
            getExecutor());

    List<Integer> dataList = resultFutures.join();

    System.out.println(Arrays.toString(dataList.toArray()));
    System.out.println("Max: " + Collections.max(dataList));

    Instant endTime = Instant.now();
    System.out.println("Total Execution Time: " + Duration.between(startTime, endTime).toMillis());
  }

  public static Executor getExecutor() {
    return Executors.newFixedThreadPool(10);
  }
}
