package Day4;

import AdventSolver.AdventSolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PassportProcessing extends AdventSolver {

  private ArrayList<Map<String, String>> data = new ArrayList<>();
  private int index;

  public PassportProcessing(String dataLocation) {
    super(dataLocation);
    data.add(new HashMap<>());
    index = 0;
  }

  @Override
  public void processData(String line) {
    if (line.length() == 0) {
      index++;
      data.add(new HashMap<>());
    } else {
      int i = line.indexOf(":");
      while (i >= 0) {
        String key = line.substring(i - 3, i);
        int btwn = line.indexOf(" ", i + 1);
        String value = line.substring(i + 1, btwn >= 0 ? btwn : line.length());
        data.get(index).put(key, value);
        i = line.indexOf(":", i + 1);
      }
    }
  }

  @Override
  public String solvePartOne() {
    int numValid = 0;

    for (Map<String, String> item : data) {
      if (keysAreValid(item)) numValid++;
    }
    return numValid + "";
  }

  private boolean keysAreValid(Map<String, String> id) {
    Set<String> keys = id.keySet();
    keys.remove("cid");

    return keys.equals(new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")));
  }

  @Override
  public String solvePartTwo() {
    int numValid = 0;

    for (Map<String, String> item : data) {
      if (!keysAreValid(item)) {
        System.out.println("here");
        continue;
      }
      try {
        if (testBYR(item.get("byr")) && testIYR(item.get("iyr")) && testEYR(item.get("eyr")) &&
                testHGT(item.get("hgt")) && testHCL(item.get("hcl")) && testECL(item.get("ecl")) &&
                testPID(item.get("pid"))) {
          numValid++;
        }
      } catch (NumberFormatException e) {
        System.out.println("error :(");
        continue;
      }
      //System.out.println("looked at " + item);
    }
    return numValid + "";
  }

  private boolean testBYR(String byr) {
    return inRange(byr, 1920, 2002);
  }

  private boolean testIYR(String iyr) {
    return inRange(iyr, 2010, 2020);
  }

  private boolean testEYR(String eyr) {
    return inRange(eyr, 2020, 2030);
  }

  private boolean testHGT(String hgt) {
    String units = hgt.substring(hgt.length() - 2);
    String num = hgt.substring(0, hgt.length() - 2);
    if (units.equals("cm")) {
      return inRange(num, 150, 193);
    } else if (units.equals("in")) {
      return inRange(num, 59, 76);
    } else {
      return false;
    }
  }

  private boolean inRange(String input, int min, int max) {
    int intgr = Integer.parseInt(input);
    return (intgr >= min && intgr <= max);
  }

  private boolean testHCL(String hcl) {
    return hcl.matches("#[\\da-f]{6}");
  }

  private boolean testECL(String ecl) {
    return new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth")).contains(ecl);
  }

  private boolean testPID(String pid) {
    return pid.matches("\\d{9}");
  }

  public static void main(String[] args) {
    AdventSolver solver = new PassportProcessing("src/Day4/data");
    solver.solve();
  }
}
