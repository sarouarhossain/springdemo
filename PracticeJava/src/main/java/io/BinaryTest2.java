package io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class BinaryTest2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    //
    Charset utf8 = Charset.forName("UTF-8");
    try (BufferedReader in =
            new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(
                        "C:\\Users\\sarou\\Desktop\\Project\\demo\\PracticeJava\\src\\main\\java\\io\\input.txt"),
                    utf8));
        PrintWriter out =
            new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream(
                        "C:\\Users\\sarou\\Desktop\\Project\\demo\\PracticeJava\\src\\main\\java\\io\\output.txt"),
                    utf8)); ) {
      String line = null;
      while ((line = in.readLine()) != null) {
        System.out.println(line);
        out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
