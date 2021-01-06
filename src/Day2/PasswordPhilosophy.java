package Day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PasswordPhilosophy {

  private static Map<Triplet<Integer, Integer, String>, String> data = new HashMap<>();

  private static int readData() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader("src/Day2/data"));
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

      Triplet<Integer, Integer, String> key = new Triplet<>();

      int indexOfDash = line.indexOf("-");
      key.setLower(Integer.parseInt(line.substring(0, indexOfDash)));

      int indexOfSpace = line.indexOf(" ");
      key.setUpper(Integer.parseInt(line.substring(indexOfDash + 1, indexOfSpace)));

      key.setLetter(line.substring(indexOfSpace + 1, indexOfSpace + 2));

      data.put(key, line.substring(line.indexOf(' ', indexOfSpace + 1) + 1));
    }

    return 0;
  }

  private static int howManyValid() {
    int sum = 0;
    for (Triplet<Integer, Integer, String> key : data.keySet()) {
      if (isValid(key, data.get(key))) sum += 1;
    }
    return sum;
  }

  private static boolean isValid(Triplet<Integer, Integer, String> key, String password) {
    int count = 0;
    char letter = key.letter.toCharArray()[0];
    for (char car : password.toCharArray()) {
      if (car == letter) count += 1;
    }

    return count >= key.lower && count <= key.upper;
  }

  public static void main(String[] args) {
    if (readData() == -1) {
      System.out.println("ERROR IN READING DATA, QUITTING");
      return;
    }
    System.out.println(data.keySet().size());
    System.out.println("Answer: " + howManyValid());
  }

}

class Triplet<T, R, S> {

  public T lower;
  public R upper;
  public S letter;

  public Triplet(T lower, R upper, S ch) {
    this.lower = lower;
    this.upper = upper;
    this.letter = ch;
  }

  public Triplet() {
  }

  public void setLower(T lower) {
    this.lower = lower;
  }

  public void setUpper(R upper) {
    this.upper = upper;
  }

  public void setLetter(S letter) {
    this.letter = letter;
  }

  @Override
  public String toString() {
    return lower + "-" + upper + " " + letter + ": ";
  }

}
