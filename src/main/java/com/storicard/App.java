package com.storicard;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import com.storicard.model.Edge;
import com.storicard.service.EdgeService;
import com.storicard.util.FileUtil;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("[INPUT] Type the file absolute path:");
    String path = scanner.nextLine();
    Stream<String> lines;
    try {
      lines = FileUtil.getFiles(path.trim());
      EdgeService edgeService = new EdgeService();

      List<Edge> edges = edgeService.generateEdges(lines);
      // System.out.println("building edges");
      // edges.forEach(System.out::println);

      edgeService.sort(edges);
      // System.out.println("sorted building edges");
      // edges.forEach(System.out::println);

      List<List<Integer>> coordinates = edgeService.getCoordinates(edges);
      System.out.println("[OUTPUT] Coordinates:");
      System.out.println(coordinates);
    } catch (IOException e) {
      System.err.println(String.format("An error has occurred: %s", e.getMessage()));
    } finally {
      scanner.close();
    }
  }
}
