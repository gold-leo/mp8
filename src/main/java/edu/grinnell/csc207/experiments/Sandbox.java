package edu.grinnell.csc207.experiments;

import java.util.Comparator;
import edu.grinnell.csc207.sorting.*;

public class Sandbox {
  public static void main(String[] args) {
    Comparator<String> order = (x,y) -> x.compareTo(y);
    InsertionSorter<String> stringSorter = new InsertionSorter<String>(order);

    System.out.println(order.compare("d", "b"));

    String[] str = {"d", "b", "c"};
    stringSorter.sort(str);
    for (String i : str) {
      System.err.printf(i + " ");
    }
  }
}
