package functionalInterfaces;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test3 {
  public static void main(String[] args) {
    //
    Person p1 = new Person("David", 21, "123");
    Person p2 = new Person("David", 21, "124");
    Person p3 = new Person("David", 21, "123");
    Person p4 = new Person("David", 23, "123");
    Person p5 = new Person("Mills", 45, "987");
    Person p6 = new Person("Mills", 21, "987");
    Person p7 = new Person("Mills", 45, "987");
    Person[] persons = {p1, p2, p3, p4, p5, p6, p7};
    List<Person> list = Arrays.asList(persons);

    HashSet<String> set = new HashSet<>();
    list.stream()
        .filter(person -> set.add(person.name.toLowerCase() + person.age))
        .forEach(System.out::println);

    //    list.stream()
    //        .filter(distinctByMultipleProperty(person -> Arrays.asList(person.name, person.age)))
    //        .forEach(System.out::println);
  }

  static <T> Predicate<T> distinctByMultipleProperty(Function<? super T, List> keyExtractors) {
    Set<List> seen = new HashSet<>();
    // return t -> seen.add(keyExtractors.apply(t));
    System.out.println("Came here....");
    // Set<Object> seen = new HashSet<>();
    Predicate<T> predicate =
        t -> {
          System.out.println("T: " + t);
          var x = keyExtractors.apply(t);
          System.out.println("Key Extractor: " + x);
          return seen.add(x);
        };
    System.out.println("Hash: " + predicate.hashCode());
    return predicate;
  }
}

class Person {
  String name;
  int age;
  String id;

  public Person(String name, int age, String id) {
    this.name = name;
    this.age = age;
    this.id = id;
  }

  @Override
  public String toString() {
    return "Person{" + "name='" + name + '\'' + ", age=" + age + ", id='" + id + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return age == person.age && Objects.equals(name, person.name) && Objects.equals(id, person.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, id);
  }
}
