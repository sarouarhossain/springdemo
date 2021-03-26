package io;

import java.io.*;

public class BinaryTest {
  public static void main(String[] args) {
    //
    try (InputStream in =
            new FileInputStream(
                "/home/sarouarhossain/Documents/TestSpring/springdemo/PracticeJava/src/main/java/io/input.txt");
        OutputStream out =
            new FileOutputStream(
                "/home/sarouarhossain/Documents/TestSpring/springdemo/PracticeJava/src/main/java/io/output.txt")) {
      byte[] buffer = new byte[1024];
      int i = 0;
      while ((i = in.read(buffer)) != -1) {
        System.out.println("TEST: " + new String(buffer));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
