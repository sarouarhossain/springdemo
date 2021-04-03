package functionalInterfaces;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Test {
  public static void main(String[] args) {
    //
    //    List<String> names = Arrays.asList("Smith", "Gourav", "Heather", "John", "Catania");
    //    Function<String, Integer> func =
    //        (s) -> {
    //          return s.length();
    //        };
    //    Function<Integer, Integer> func1 = t -> t * t;
    //
    //    var func3 = func1.compose(func);
    //    names.stream().map(func3).forEach(System.out::println);

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
