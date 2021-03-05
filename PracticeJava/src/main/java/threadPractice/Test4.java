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

public class Test4 {
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

    HttpClient client = HttpClient.newHttpClient();
    List<Integer> dataList = new ArrayList<>();
    for (String uri : uris) {
      var res =
          client
              .send(
                  HttpRequest.newBuilder(new URI(uri)).GET().build(),
                  HttpResponse.BodyHandlers.ofString())
              .body();
      dataList.add(Integer.parseInt(res));
    }

    System.out.println(Arrays.toString(dataList.toArray()));
    System.out.println("Max: " + Collections.max(dataList));

    Instant endTime = Instant.now();
    System.out.println("Total Execution Time: " + Duration.between(startTime, endTime).toMillis());
  }
}
