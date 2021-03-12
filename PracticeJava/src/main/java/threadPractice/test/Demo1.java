package threadPractice.test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class Demo1 {
  private final Executor executor;

  Demo1() {
    this.executor = this.executor();
  }

  public static void main(String[] args) {
    //
    System.out.println("Process start: " + Thread.currentThread().getName());
    var timeStart = Instant.now();
    Demo1 demo1 = new Demo1();
    var x = demo1.printRes().join();
    var timeEnd = Instant.now();
    System.out.println("Execution time: " + Duration.between(timeStart, timeEnd).toMillis());
    System.out.println("Process end: " + Thread.currentThread().getName());
  }

  public CompletableFuture<Void> printRes() {
    var allIs = getIs();
    var allJs = getJs();
    var allKs = getKs();

    return CompletableFuture.allOf(allIs, allJs, allKs)
        .thenApplyAsync(
            res -> {
              var iS = allIs.join();
              var jS = allJs.join();
              var kS = allKs.join();
              System.out.println("Print thread:  " + Thread.currentThread().getName());
              System.out.println(iS);
              System.out.println(jS);
              System.out.println(kS);

              return null;
            },
            executor);
  }

  CompletableFuture<List<String>> getIs() {
    return supplyAsync(
        () -> {
          var threadName = Thread.currentThread().getName();
          List<String> res = new ArrayList<>();
          for (int i = 0; i < 10; i++) {
            res.add(threadName + " i=" + i);
            sleep(500);
          }
          return res;
        },
        executor);
  }

  CompletableFuture<List<String>> getJs() {
    return supplyAsync(
        () -> {
          var threadName = Thread.currentThread().getName();
          List<String> res = new ArrayList<>();
          for (int j = 0; j < 10; j++) {
            res.add(threadName + " j=" + j);
            sleep(500);
          }
          return res;
        },
        executor);
  }

  CompletableFuture<List<String>> getKs() {
    return supplyAsync(
        () -> {
          var threadName = Thread.currentThread().getName();
          List<String> res = new ArrayList<>();
          for (int k = 0; k < 10; k++) {
            res.add(threadName + " j=" + k);
            sleep(500);
          }
          return res;
        },
        executor);
  }

  public void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private ExecutorService executor() {
    return Executors.newFixedThreadPool(3);
  }
}
