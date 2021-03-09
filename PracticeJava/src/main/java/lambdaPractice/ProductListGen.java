package lambdaPractice;

import java.util.ArrayList;
import java.util.List;

public class ProductListGen {
  public static List<Product> getList() {
    Product product1 = new Product("David", "Pen", 10);
    Product product2 = new Product("David", "Apple", 5);
    Product product3 = new Product("Rafi", "Mango", 7);
    Product product4 = new Product("Rafi", "Pencil", 12);
    Product product5 = new Product("Ali", "Apple", 10);
    Product product6 = new Product("Rafi", "Radio", 30);
    Product product7 = new Product("Ali", "Mobile", 9);

    List<Product> list = new ArrayList<>();
    list.add(product1);
    list.add(product2);
    list.add(product3);
    list.add(product4);
    list.add(product5);
    list.add(product6);
    list.add(product7);

    return list;
  }
}
