package threadPractice.test;

import java.io.FileNotFoundException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

public class Demo5 {
  public static void main(String[] args) {
    //
    Demo5 demo5 = new Demo5();

    var res =
        demo5
            .test1()
            .thenComposeAsync(
                x -> {
                  System.out.println("came here" + x);

                  return CompletableFuture.supplyAsync(
                      () -> {
                        try {
                          throw new Exception("abc");
                        } catch (Exception e) {
                          e.printStackTrace();
                          // throw new CompletionException(e.getCause());
                        }
                        return x;
                      });
                })
            .exceptionally(
                e -> {
                  System.out.println("in exception" + e.getMessage());
                  return null;
                });
    res.toCompletableFuture().join();
  }

  CompletionStage<String> test1() {
    return CompletableFuture.supplyAsync(
        () -> {
          //          try {
          //            throw new Exception("Hello Exception");
          //          } catch (Exception e) {
          //            e.printStackTrace();
          //            // throw new CompletionException(e.getCause());
          //          }
          return "hello";
        });
  }
}
