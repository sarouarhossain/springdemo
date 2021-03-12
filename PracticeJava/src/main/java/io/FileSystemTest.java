package io;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemTest {
  public static void main(String[] args) {
    //
    FileSystem fs = FileSystems.getDefault();
    fs.getFileStores().forEach(s -> System.out.println(s.type() + "  " + s.name()));
    fs.getRootDirectories().forEach(s -> System.out.println(s));
    var separator = fs.getSeparator();
    System.out.println(separator);

    Path path = Path.of("C:\\", "roman", "docs", "test.txt");
    System.out.println(path.getFileName());
    System.out.println(path.getParent());
    System.out.println(Path.of("."));
  }
}
