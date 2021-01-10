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
      key.setFirst(Integer.parseInt(line.substring(0, indexOfDash)));

      int indexOfSpace = line.indexOf(" ");
      key.setSecond(Integer.parseInt(line.substring(indexOfDash + 1, indexOfSpace)));

      key.setLetter(line.substring(indexOfSpace + 1, indexOfSpace + 2));

      data.put(key, line.substring(line.indexOf(' ', indexOfSpace + 1) + 1));
    }

    return 0;
  }

  private static int howManyValid(int part) {
    int sum = 0;

    if (part == 1) {
      for (Triplet<Integer, Integer, String> key : data.keySet()) {
        if (isValid1(key, data.get(key))) sum += 1;
      }
    } else {
      for (Triplet<Integer, Integer, String> key : data.keySet()) {
        if (isValid2(key, data.get(key))) sum += 1;
      }
    }
    return sum;
  }

  private static boolean isValid1(Triplet<Integer, Integer, String> key, String password) {
    int count = 0;
    char letter = key.letter.toCharArray()[0];
    for (char car : password.toCharArray()) {
      if (car == letter) count += 1;
    }

    return count >= key.first && count <= key.second;
  }

  private static boolean isValid2(Triplet<Integer, Integer, String> key, String password) {
    char letter = key.letter.toCharArray()[0];

    return (password.charAt(key.first - 1) == letter && password.charAt(key.second - 1) != letter) ||
               (password.charAt(key.first - 1) != letter && password.charAt(key.second - 1) == letter);
  }

  public static void main(String[] args) {
    if (readData() == -1) {
      System.out.println("ERROR IN READING DATA, QUITTING");
      return;
    }
    System.out.println(data.keySet().size());
    System.out.println("Answer for part 1: " + howManyValid(1));
    System.out.println("Answer for part 2: " + howManyValid(2));
  }

}

class Triplet<T, R, S> {

  public T first;
  public R second;
  public S letter;

  public Triplet(T first, R second, S ch) {
    this.first = first;
    this.second = second;
    this.letter = ch;
  }

  public Triplet() {
  }

  public void setFirst(T first) {
    this.first = first;
  }

  public void setSecond(R second) {
    this.second = second;
  }

  public void setLetter(S letter) {
    this.letter = letter;
  }

  @Override
  public String toString() {
    return first + "-" + second + " " + letter + ": ";
  }

}
