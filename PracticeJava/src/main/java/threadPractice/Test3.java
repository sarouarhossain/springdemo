package threadPractice;

import java.io.IOException;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Test3 {
  public static void main(String[] args)
      throws URISyntaxException, IOException, InterruptedException {
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

    List<Callable<String>> callableTask = new ArrayList<>();
    HttpClient client = HttpClient.newHttpClient();
    for (String uri : uris) {
      Callable<String> callable =
          () -> {
            var res =
                client.send(
                    HttpRequest.newBuilder(new URI(uri)).GET().build(),
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

    System.out.println(Arrays.toString(dataList.toArray()));
    System.out.println("Max: " + Collections.max(dataList));
    executorService.shutdown();
    Instant endTime = Instant.now();
    System.out.println("Total Execution Time: " + Duration.between(startTime, endTime).toMillis());
  }
}
