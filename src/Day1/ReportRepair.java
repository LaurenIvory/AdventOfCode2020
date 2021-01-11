package Day1;

import AdventSolver.AdventSolver;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class ReportRepair extends AdventSolver {

  private static ArrayList<Integer> data = new ArrayList<>();

  public ReportRepair(String dataLocation) {
    super(dataLocation);
  }

  @Override
  public void processData(String line) {
    data.add(Integer.parseInt(line));
  }

  @Override
  public String solvePartOne() {
    data.sort(Comparator.naturalOrder());
    return calculateForTwo(-1) + "";
  }

  @Override
  public String solvePartTwo() {
    return calculateForThree() + "";
  }

  private static int calculateForTwo(int idxToSkip) {
    int goal;
    if (idxToSkip == -1) {
      goal = 2020;
    } else {
      goal = 2020 - data.get(idxToSkip);
    }

    int upper = data.size() - 1;
    int lower = 0;

    int[] skipped = skip(upper, lower, idxToSkip);
    upper = skipped[0];
    lower = skipped[1];

    while (lower < upper) {
      int upperVal = data.get(upper);
      int lowerVal = data.get(lower);

      int sum = upperVal + lowerVal;

      if (sum == goal) {
        return upperVal * lowerVal;
      } else if (sum > goal) {
        upper -= 1;
      } else {
        lower += 1;
      }

      skipped = skip(upper, lower, idxToSkip);
      upper = skipped[0];
      lower = skipped[1];

    }

    return -1;

  }

  private static int[] skip(Integer upper, Integer lower, int idxToSkip) {
    if (upper.equals(idxToSkip)) {
      upper -= 1;
    } else if (lower.equals(idxToSkip)) {
      lower += 1;
    }

    return new int[] {upper, lower};
  }

  private static int calculateForThree() {
    for (int i = 0; i < data.size(); i++) {
      int trial = calculateForTwo(i);
      if (trial > -1) {
        return trial * data.get(i);
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    AdventSolver solver = new ReportRepair("src/Day1/data");
    solver.solve();
  }

}
