package threadPractice.test;

import java.util.ArrayList;
import java.util.List;

public class ABC {
  public static void main(String[] args) {
    //
    List<Integer> list = new ArrayList<>();
    list.add(5);
    System.out.println(list);
    list.remove(new Integer(5));
    System.out.println(list);
    list.add(4);
    System.out.println(list);
  }
}
