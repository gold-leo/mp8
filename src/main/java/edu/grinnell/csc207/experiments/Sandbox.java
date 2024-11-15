package edu.grinnell.csc207.experiments;

import java.util.Comparator;
import edu.grinnell.csc207.sorting.*;

public class Sandbox {
  public static void main(String[] args) {
    Comparator<Integer> order = (x,y) -> x.compareTo(y);
    Quicksorter<Integer> stringSorter = new Quicksorter<Integer>(order);

    Integer[] str = {0, 1, 2, 3, 4};
    stringSorter.sort(str);
    for (Integer i : str) {
      System.err.printf(i + " ");
    }
  }
}
