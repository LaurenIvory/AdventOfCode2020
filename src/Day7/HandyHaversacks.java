package Day7;

import AdventSolver.AdventSolver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

public class HandyHaversacks extends AdventSolver {

  private Graph<String, DefaultWeightedEdge> g;

  public HandyHaversacks(String dataLocation) {
    super(dataLocation);
    g = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
  }

  @Override
  public void processData(String line) {
    String thisNode = line.substring(0, line.indexOf(" bags contain"));
    g.addVertex(thisNode);

    int beginIdx = line.indexOf("contain") + "contain".length();
    while (beginIdx != -1) {
      beginIdx += 1;
      int endNumIdx = line.indexOf(" ", beginIdx + 1);
      String num = line.substring(beginIdx, endNumIdx);
      System.out.println(num);
      beginIdx = endNumIdx + 1;
      int endNodeIdx = line.indexOf("bag", beginIdx) - 1;
      String nghbr = line.substring(beginIdx, endNodeIdx);
      g.addVertex(nghbr);

      // To solve part 1, use this line
      // g.addEdge(nghbr, thisNode);

      // To solve part 2, use this line
      g.setEdgeWeight(g.addEdge(thisNode, nghbr), Integer.parseInt(num));

      beginIdx = line.indexOf(",", endNodeIdx);
    }

  }

  @Override
  public String solvePartOne() {
    Iterator<String> iterator = new DepthFirstIterator<>(g, "shiny gold");
    int count = 0;
    System.out.println(iterator.next());
    while (iterator.hasNext()) {
      String dummy = iterator.next();
      count++;
    }
    return count + "";
  }

  @Override
  public String solvePartTwo() {
    Map<String, Integer> map = new HashMap<>();

    return numSubBags("shiny gold", map) + "";
  }

  private Integer numSubBags(String thisNode, Map<String, Integer> map) {
    Integer sum = 0;
    System.out.println("thisNode: " + thisNode);
    for (DefaultWeightedEdge e : g.outgoingEdgesOf(thisNode)) {
      System.out.println("target: " + g.getEdgeTarget(e));
      sum += (int) g.getEdgeWeight(e) + (int) g.getEdgeWeight(e) * map.getOrDefault(g.getEdgeTarget(e), numSubBags(g.getEdgeTarget(e), map));
    }
    return sum;
  }

  public static void main(String[] args) {
    AdventSolver solver = new HandyHaversacks("src/Day7/dataTest");
    solver.solve();
  }
}
