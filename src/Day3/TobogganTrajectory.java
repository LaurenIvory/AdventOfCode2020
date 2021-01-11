package Day3;

import AdventSolver.AdventSolver;
import java.util.ArrayList;

public class TobogganTrajectory extends AdventSolver {

  private ArrayList<String> data = new ArrayList<>();

  private TobogganTrajectory(String dataLoc) {
    super(dataLoc);
  }

  @Override
  public void processData(String line) {
    data.add(line);
  }

  private long treesOnRoute(int rightMovement, int downMovement) {
    int rowLen = data.get(0).length();

    int numTrees = 0;
    int col = 0;
    for (int row = 0; row < data.size(); row+=downMovement) {
      if (data.get(row).toCharArray()[col] == '#') {
        numTrees += 1;
      }
      col = (col + rightMovement) % rowLen;
    }
    return numTrees;
  }

  @Override
  public String solvePartOne() {
    return treesOnRoute(3, 1) + "";
  }

  @Override
  public String solvePartTwo() {
    return (treesOnRoute(1, 1) * treesOnRoute(3, 1) *
                treesOnRoute(5, 1) * treesOnRoute(7, 1) *
                treesOnRoute(1, 2)) + "";
  }

  public static void main(String[] args) {
    AdventSolver solver = new TobogganTrajectory("src/Day3/data");
    solver.solve();
  }

}


