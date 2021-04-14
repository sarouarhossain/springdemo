package cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

public class Test {
  public static void main(String[] args) {
    //
    Test test = new Test();
    var x = test.firstChain(5, 0).toCompletableFuture().join();
    System.out.println(x);
  }

  public CompletionStage<Integer> firstChain(Integer x, Integer y) {

    return secondChain(x, y)
        .thenApplyAsync(
            res -> {
              return res + 1;
            })
        .exceptionally(
            ex -> {
              throw new CompletionException(ex.getCause());
            });
  }

  public CompletionStage<Integer> secondChain(Integer x, Integer y) {
    return thirdChain(x, y)
        .thenApplyAsync(
            res -> {
              return res + 1;
            })
        .exceptionally(
            ex -> {
              throw new CompletionException(ex.getCause());
            });
  }

  public CompletionStage<Integer> thirdChain(Integer x, Integer y) {
    return getInt(x, y)
        .thenApplyAsync(
            res -> {
              return res + 1;
            })
        .exceptionally(
            ex -> {
              throw new CompletionException(ex.getCause());
            });
  }

  public CompletionStage<Integer> getInt(Integer x, Integer y) {
    return CompletableFuture.supplyAsync(
        () -> {
          return x / y;
        });
  }
}
