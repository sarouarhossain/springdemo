package lambdaPractice;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
  public static void main(String[] args) {
    //
    List<Product> list = ProductListGen.getList();

    var sum1 = list.stream().parallel().mapToDouble(p -> p.getPrice()).sum();
    var sum2 = list.stream().mapToInt(p -> p.getPrice()).sum();
    System.out.println(sum1 + "   " + sum2);

    var sum3 = list.stream().mapToInt(p -> p.getPrice()).average();
    System.out.println(sum3.getAsDouble());

    list.stream()
        .parallel()
        .filter(p -> p.getPrice() >= 10)
        .forEach(
            p -> {
              //              System.out.println(
              //                  "Thread: " + Thread.currentThread().getName() + ", Product:" +
              // p.getName());
              p.setDiscount(0.2);
            });
    System.out.println("Products: " + list);

    list.stream().peek(p -> p.setName(p.name + "11")).limit(2).forEach(x -> System.out.println(x));
    System.out.println("-----------------------------------------");

    list.stream().forEach(p -> System.out.println(p));
    System.out.println("-----------------------------------------");
    System.out.println(list);

    list.stream()
        .filter(p -> p.getDiscount() == null)
        .peek(p -> p.setDiscount(.3))
        // .forEach(p -> System.out.println(p));
        .forEach(p -> p.setUserName(p.userName + "U"));
    System.out.println(list);
    System.out.println("-----------------------------------------");
    var lists =
        list.stream()
            .filter(p -> p.getDiscount() == null)
            .peek(p -> p.setDiscount(.3))
            .collect(Collectors.toList());
    lists.stream().forEachOrdered(p -> System.out.println(p));

    System.out.println("-------------------------------------------------");
    var sum =
        Stream.of("One", "Two", "Three", "Four", "Five")
            .mapToInt(s -> s.length())
            .peek(i -> System.out.println(i))
            .sum();
    System.out.println(sum);
  }
}
