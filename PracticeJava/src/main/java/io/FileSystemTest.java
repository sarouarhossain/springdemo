package io;

import java.io.IOException;
import java.nio.file.*;

public class FileSystemTest {
  public static void main(String[] args) throws IOException {
    //
    //    FileSystem fs = FileSystems.getDefault();
    //    fs.getFileStores().forEach(s -> System.out.println(s.type() + "  " + s.name()));
    //    fs.getRootDirectories().forEach(s -> System.out.println(s));
    //    var separator = fs.getSeparator();
    //    System.out.println(separator);
    //
    //    Path path = Path.of("/", "roman", "docs", "test.txt");
    //    System.out.println(path.getFileName());
    //    System.out.println(path.getParent());
    //    System.out.println(Paths.get("").toAbsolutePath().toString());

    Path joe = Path.of("/home/sarouarhossain/", "users", "joe");
    Path p1 = Path.of("/home/sarouarhossain/", "users", "joe", "docs", "some.txt");
    System.out.println(p1.toAbsolutePath());
    for (int i = 0; i < p1.getNameCount(); i++) {
      Path p = p1.getName(i);
      System.out.println(p.toString());
    }

    Path p2 = Path.of("/home/sarouarhossain/", "users", "joe", "pics", "s.txt");
    System.out.println(p2.toAbsolutePath());

    if (!Files.exists(p1)) {
      Files.createDirectories(p1.getParent());
      Files.createFile(p1);
    }

    Files.createDirectories(p2.getParent());
    if (Files.exists(p2)) {
      Files.delete(p2);
    }
    Files.createSymbolicLink(p2, p1);
    //    //    Files.list(joe).forEach(p -> System.out.println(p));
    //    Files.list(joe).forEach(p -> System.out.println(p));
    //    Files.walk(joe)
    //        .map(p -> p.toString())
    //        .filter(s -> s.endsWith("txt"))
    //        .forEach(p -> System.out.println(p));
    Path p3 = Files.readSymbolicLink(p2);
    System.out.println(p3.toString());
  }
}
