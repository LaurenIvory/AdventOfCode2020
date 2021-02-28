package Day6;

import AdventSolver.AdventSolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomCustoms extends AdventSolver {

  private ArrayList<ArrayList<Set<Character>>> data = new ArrayList<>();
  private int group;

  public CustomCustoms(String dataLocation) {
    super(dataLocation);
    data.add(new ArrayList<>());
    group = 0;
  }

  @Override
  public void processData(String line) {
    if (line.length() == 0) {
      group++;
      data.add(new ArrayList<>());
    } else {
      data.get(group).add(toSet(line));
    }
  }

  private Set<Character> toSet(String line) {
    Set<Character> list = new HashSet<>();
    for (char c : line.toCharArray()) {
      list.add(c);
    }
    return list;
  }

  @Override
  public String solvePartOne() {
    return data.stream().map((list) -> {
      Set<Character> set = new HashSet<>();
      for (Set<Character> s : list) {
        set.addAll(s);
      }
      return set;
    }).map(Set::size).reduce(0, Integer::sum) + "";
  }

  @Override
  public String solvePartTwo() {
    return data.stream().map((list) -> {
      Set<Character> set = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
      for (Set<Character> s : list) {
        set.retainAll(s);
      }
      return set;
    }).map(Set::size).reduce(0, Integer::sum) + "";
  }

  public static void main(String[] args) {
    AdventSolver solver = new CustomCustoms("src/Day6/data");
    solver.solve();
  }
}
