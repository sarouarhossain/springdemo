package io;

import java.io.*;
import java.nio.charset.Charset;

public class CharacterTest {
  public static void main(String[] args) {
    //
    Charset utf8 = Charset.forName("UTF-8");
    try (Reader in =
            new FileReader(
                "C:\\Users\\sarou\\Desktop\\Project\\demo\\PracticeJava\\src\\main\\java\\io\\input.txt",
                utf8);
        Writer out =
            new FileWriter(
                "C:\\Users\\sarou\\Desktop\\Project\\demo\\PracticeJava\\src\\main\\java\\io\\output.txt",
                utf8)) {
      char[] buffer = new char[1024];
      int i = 0;
      while ((i = in.read(buffer)) != -1) {
        System.out.println(buffer);
        out.write(buffer, 0, i);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
