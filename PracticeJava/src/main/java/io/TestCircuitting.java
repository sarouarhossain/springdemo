package io;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TestCircuitting {
  public static void main(String[] args) {
    //    System.out.println(
    //        new BigInteger(
    //
    // "9876654376616266616266129876543319987216555431666127777317889912998831771727126")
    //            .add(new BigInteger("2")));
    //    int[] x = new int[Integer.MAX_VALUE];
    //    List<Object> list = new ArrayList<>();
    //    String st = new String();
    //    BigInteger xx = new BigInteger("123");
    //    xx.toString();

    if (firstCondition() | secondCondition() | thirdCondition()) {}
  }

  static void test() {
    System.out.println();
  }

  static boolean firstCondition() {
    System.out.println("Came to first Condition");
    return true;
  }

  static boolean secondCondition() {
    System.out.println("Came to second Condition");
    return false;
  }

  static boolean thirdCondition() {
    System.out.println("Came to third Condition");
    return true;
  }
}
