package AdventSolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class AdventSolver {

  private String dataLocation;

  public AdventSolver(String dataLocation) {
    this.dataLocation = dataLocation;
  }

  public int readData() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(dataLocation));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return -1;
    }

    String line;
    while (true) {
      try {
        if ((line = reader.readLine()) == null) break;
      } catch (IOException e) {
        e.printStackTrace();
        return -1;
      }
      processData(line);
    }

    return 0;
  }

  abstract public void processData(String line);

  abstract public String solvePartOne();

  abstract public String solvePartTwo();

  public void solve() {
    if (readData() == -1) {
      System.out.println("ERROR IN READING DATA, QUITTING");
      return;
    }
    System.out.println("Answer to part 1: " + solvePartOne());
    System.out.println("Answer to part 2: " + solvePartTwo());
  }
}
