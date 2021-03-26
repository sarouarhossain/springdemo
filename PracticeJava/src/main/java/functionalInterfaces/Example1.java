package functionalInterfaces;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Example1 {
  static List<String> testMyFunc(List<String> list, MyFunction func) {
    List<String> newList = new ArrayList<>();
    for (String x : list) {
      newList.add(func.apply(x));
    }
    return newList;
  }

  public static void main(String[] args) {
    // Functional interface has Single Abstract method apply() which accepts arguments T and return
    // R
    List<String> names = Arrays.asList("Smith", "Gourav", "Heather", "John", "Catania");
    Function<String, Integer> nameSize = String::length;
    var newList = names.stream().map(nameSize).collect(Collectors.toList());
    System.out.println(newList);
    System.out.println("----------------------------------------------------------");
    var newList1 = testMyFunc(names, s -> s.concat(" A"));
    System.out.println(newList1);
    System.out.println("----------------------------------------------------------");

    // For two arguments
    Map<String, Integer> salaries = new HashMap<>();
    salaries.put("John", 40000);
    salaries.put("Freddy", 30000);
    salaries.put("Samuel", 50000);
    BiFunction<String, Integer, Integer> bf =
        (name, oldValue) -> name.equals("Freddy") ? oldValue : oldValue + 5000;
    salaries.replaceAll(bf);
    System.out.println(salaries);
  }
}
