package com.storicard.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtil {

  public static Stream<String> getFiles(String filename) throws IOException {
    Stream<String> lines = Files.lines(Paths.get(filename));
    return lines;
  }

}
