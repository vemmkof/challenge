package com.storicard.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;
import com.storicard.model.Edge;

public class EdgeService {

  public List<Edge> generateEdges(Stream<String> lines) {
    List<Edge> edges = new ArrayList<Edge>();
    lines.forEach(line -> {
      int[] points = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
      edges.add(Edge.builder().x(points[0]).h(points[2]).isLeft(true).build());
      edges.add(Edge.builder().x(points[1]).h(points[2]).isLeft(false).build());
    });
    return edges;
  }

  public void sort(List<Edge> edges) {
    Collections.sort(edges, new Comparator<Edge>() {
      @Override
      public int compare(Edge o1, Edge o2) {
        if (o1.getX() == o2.getX()) { // same x, diff height
          return Integer.compare(o1.getH(), o2.getH());
        } else {// first from left to right
          return Integer.compare(o1.getX(), o2.getX());
        }
      }
    });
  }

  public List<List<Integer>> getCoordinates(List<Edge> edges) {
    List<Integer> coordinate;
    List<List<Integer>> coordinates = new ArrayList<List<Integer>>();
    PriorityQueue<Integer> heightPriorityQueue =
        new PriorityQueue<Integer>(Collections.reverseOrder());
    for (Edge edge : edges) {
      if (edge.isLeft()) { // solve start
        if (heightPriorityQueue.isEmpty() || edge.getH() > heightPriorityQueue.peek()) {
          coordinate = new ArrayList<Integer>();
          coordinate.add(edge.getX());
          coordinate.add(edge.getH());
          coordinates.add(coordinate);
        }
        heightPriorityQueue.add(edge.getH());
      } else { // solve end
        heightPriorityQueue.remove(edge.getH());
        coordinate = new ArrayList<Integer>();
        if (heightPriorityQueue.isEmpty()) { // h = 0
          coordinate.add(edge.getX());
          coordinate.add(0);
          coordinates.add(coordinate);
        } else if (edge.getH() > heightPriorityQueue.peek()) { // h = pq
          coordinate.add(edge.getX());
          coordinate.add(heightPriorityQueue.peek());
          coordinates.add(coordinate);
        }
      }
    }
    return coordinates;
  }

}
