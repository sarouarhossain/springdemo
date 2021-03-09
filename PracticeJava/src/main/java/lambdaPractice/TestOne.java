package lambdaPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestOne {

  public static void main(String[] args) {
    //
    Product product1 = new Product("Pen", 10);
    Product product2 = new Product("Apple", 5);
    Product product3 = new Product("Mango", 7);
    Product product4 = new Product("Pencil");
    Product product5 = new Product("Apple", 10);
    Product product6 = new Product("Radio");
    Product product7 = new Product("Mobile");

    List<Product> list = new ArrayList<>();
    list.add(product1);
    list.add(product2);
    list.add(product3);
    list.add(product4);
    list.add(product5);
    list.add(product6);
    list.add(product7);

    Comparator<Product> sorName = (p1, p2) -> p1.name.compareTo(p2.name);
    Comparator<Product> sorPrice = (p1, p2) -> p1.price.compareTo(p2.price);
    Collections.sort(list, sorName.thenComparing(sorPrice));

    list.forEach(x -> System.out.println(x.toString()));
  }
}
