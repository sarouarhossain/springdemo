package threadPractice.test;

import java.util.Optional;

public class Demo6 {
  public static void main(String[] args) {
    //
    var name = "abc";
    B b = new B();
    var x = Optional.ofNullable(b.c.y).orElse("AAA");
    System.out.println(x);
  }
}

class B {
  C c = new C();
}

class C {
  String y;
}
