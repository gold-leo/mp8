package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

import edu.grinnell.csc207.util.ArrayUtils;

public class GoldmanLeoSort<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public GoldmanLeoSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    sortHelper(values, 0, values.length - 1);;
  } // sort(T[])

  private void sortHelper(T[] v, int lb, int ub) {
    if (ub - lb > 0) {

      // Find a pivot. I chose the middle of three fourths.
      int[] ps = {lb + (ub - lb) / 2, lb + (ub - lb) / 4, lb + ((ub - lb) / 4) * 3};
      int pivot;
      if ((order.compare(v[ps[0]], v[ps[1]]) < 0 && order.compare(v[ps[1]], v[ps[2]]) < 0) || (order.compare(v[ps[2]], v[ps[1]]) < 0 && order.compare(v[ps[1]], v[ps[0]]) < 0)) {
        pivot = ps[1];
      } else if ((order.compare(v[ps[0]], v[ps[2]]) < 0 && order.compare(v[ps[2]], v[ps[1]]) < 0) || (order.compare(v[ps[1]], v[ps[2]]) < 0 && order.compare(v[ps[2]], v[ps[0]]) < 0)) {
        pivot = ps[2];
      } else {
        pivot = ps[0];
      }

      ArrayUtils.swap(v, pivot, lb);

      // Initialize the sorting indexes.
      int sm = lb + 1;
      int lg = ub;

      // Check and sort each number.
      for (; sm < lg;) {
        if (order.compare(v[sm], v[lb]) < 1) {
          sm++;
        } else {
          ArrayUtils.swap(v, sm, lg);
          lg--;
        }
      }

      // Final check
      if (order.compare(v[sm], v[lb]) < 1) {
        ArrayUtils.swap(v, lb, sm);
        sm--;
      } else {
        sm--;
        ArrayUtils.swap(v, lb, sm);
        sm--;
      }

      sortHelper(v, lb, sm);
      sortHelper(v, lg, ub);
    }
  }
} // class Quicksorter
