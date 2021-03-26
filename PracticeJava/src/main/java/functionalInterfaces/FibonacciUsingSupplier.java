package functionalInterfaces;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FibonacciUsingSupplier {
  public static void main(String[] args) {
    //
    // generateBig(100000).forEach(System.out::println);
    System.out.println(factorialBig(6));
  }

  public static List<Integer> generate(int limit) {
    return Stream.iterate(new int[] {0, 1}, s -> new int[] {s[1], s[0] + s[1]})
        .limit(limit)
        .map(n -> n[0])
        .collect(Collectors.toList());
  }

  public static List<BigInteger> generateBig(int limit) {
    return Stream.iterate(
            new BigInteger[] {BigInteger.ZERO, BigInteger.ONE},
            s -> new BigInteger[] {s[1], s[0].add(s[1])})
        .limit(limit)
        .map(n -> n[0])
        .collect(Collectors.toList());
  }

  public static Integer factorial(int num) {
    return Stream.iterate(new int[] {1, 1}, s -> new int[] {s[0] + 1, (s[0] + 1) * s[1]})
        .limit(num)
        .reduce((a, b) -> b)
        .get()[1];
  }

  public static BigInteger factorialBig(int num) {
    return Stream.iterate(
            new BigInteger[] {BigInteger.ONE, BigInteger.ONE},
            s ->
                new BigInteger[] {
                  s[0].add(BigInteger.ONE), s[0].add(BigInteger.ONE).multiply(s[1])
                })
        .limit(num)
        .reduce((a, b) -> b)
        .get()[1];
  }
}
