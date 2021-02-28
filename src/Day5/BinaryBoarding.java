package Day5;

import AdventSolver.AdventSolver;
import java.util.ArrayList;
import java.util.Comparator;

public class BinaryBoarding extends AdventSolver {

  ArrayList<BoardingPass> data = new ArrayList<>();

  public BinaryBoarding(String dataLocation) {
    super(dataLocation);
  }

  @Override
  public void processData(String line) {
    line = line.strip();
    String row = line.substring(0, 7);
    String col = line.substring(7);
    data.add(new BoardingPass(processCode(row), processCode(col)));
  }

  private int processCode(String input) {
    StringBuilder binary = new StringBuilder();
    for (char digit : input.toCharArray()) {
      if (digit == 'B' || digit == 'R') {
        binary.append("1");
      } else {
        binary.append("0");
      }
    }
    return Integer.parseInt(binary.toString(), 2);
  }

  @Override
  public String solvePartOne() {
    data.sort(Comparator.comparing(a -> a.id));
    return data.get(data.size() - 1).id + "";
  }

  @Override
  public String solvePartTwo() {
    int expected = data.get(0).id;
    int missing = -1;

    for (BoardingPass pass : data) {
      if (pass.id - expected == 1) {
        return pass.id - 1 + "";
      }
      expected = pass.id + 1;
    }
    return null;
  }

  public static void main(String[] args) {
    AdventSolver solver = new BinaryBoarding("src/Day5/data");
    solver.solve();
  }
}

class BoardingPass {
  int row;
  int col;
  Integer id;

  public BoardingPass(int row, int col) {
    this.row = row;
    this.col = col;
    this.id = (this.row * 8) + this.col;
  }

  @Override
  public String toString() {
    return row + ", " + col + ", " + id;
  }
}
