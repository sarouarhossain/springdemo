package io;

import java.io.*;

public class BinaryTest {
  public static void main(String[] args) {
    //
    try (InputStream in =
            new FileInputStream(
                "C:\\Users\\sarou\\Desktop\\Project\\demo\\PracticeJava\\src\\main\\java\\io\\input.txt");
        OutputStream out =
            new FileOutputStream(
                "C:\\Users\\sarou\\Desktop\\Project\\demo\\PracticeJava\\src\\main\\java\\io\\output.txt")) {
      byte[] buffer = new byte[1024];
      int i = 0;
      while ((i = in.read(buffer)) != -1) {
        System.out.println(i);
        System.out.println(buffer.length);
        System.out.println("TEST: " + new String(buffer));
        out.write(buffer, 0, i);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
