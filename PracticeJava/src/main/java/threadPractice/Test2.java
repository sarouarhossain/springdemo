package threadPractice;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.net.http.HttpClient;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Test2 {
  public static void main(String[] args) {
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

    getData(uris)
        .subscribe(
            x -> {
              System.out.println("Max : " + x);
              Instant endTime = Instant.now();
              System.out.println(
                  "Total Execution Time: " + Duration.between(startTime, endTime).toMillis());
            });
  }

  static Mono<Integer> getData(String[] uris) {
    WebClient webClient = WebClient.create();
    Scheduler scheduler = Schedulers.newParallel("test", 10);

    return Flux.fromArray(uris)
        .publishOn(scheduler)
        .flatMap(
            uri ->
                webClient.get().uri(uri).retrieve().bodyToMono(String.class).map(Integer::parseInt))
        .collectList()
        .map(
            list -> {
              System.out.println("Data List: " + Arrays.toString(list.toArray()));
              return Collections.max(list);
            });
  }
}
