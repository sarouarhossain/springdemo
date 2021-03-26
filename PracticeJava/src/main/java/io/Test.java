package io;

import java.io.*;

public class Test {
  public static void main(String[] args) {
    //
    try (PrintWriter out =
        new PrintWriter(
            new OutputStreamWriter(
                new FileOutputStream(
                    "/home/sarouarhossain/Documents/TestSpring/springdemo/PracticeJava/src/main/java/io/input.txt")))) {
      for (int i = 0; i < 1024 * 1024; i++) {
        String st = "I love my country.";
        out.println(st);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
