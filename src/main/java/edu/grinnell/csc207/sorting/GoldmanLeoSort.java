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
    int s = ub - lb;
    if (s == 1) {
      twoSort(v, lb, ub);
    } else if (s == 2) {
      threeSort(v, lb, ub);
    } else if (s == 3) {
      fourSort(v, lb, ub);
    } else if (s > 0) {

      // Find a pivot. I chose the middle of three fourths.
      int[] ps = {lb + (ub - lb) / 2, lb + (ub - lb) / 4, lb + ((ub - lb) / 4) * 3};
      int pivot;
      if ((order.compare(v[ps[0]], v[ps[1]]) < 0 && order.compare(v[ps[1]], v[ps[2]]) < 0) || (order.compare(v[ps[2]], v[ps[1]]) < 0 && order.compare(v[ps[1]], v[ps[0]]) < 0)) {
        pivot = ps[1];
      } else if ((order.compare(v[ps[0]], v[ps[2]]) < 0 && order.compare(v[ps[2]], v[ps[1]]) < 0) || (order.compare(v[ps[1]], v[ps[2]]) < 0 && order.compare(v[ps[2]], v[ps[0]]) < 0)) {
        pivot = ps[2];
      } else {
        pivot = ps[0];
      } // if/if/else

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

  private void twoSort(T[] v, int lb, int ub) {
    if (order.compare(v[ub], v[lb]) < 0) {
      ArrayUtils.swap(v, lb, ub);
    }
  }

  private void threeSort(T[] v, int lb, int ub) {
    if (order.compare(v[ub], v[ub - 1]) < 0) {
      ArrayUtils.swap(v, ub - 1, ub);
    }
    if (order.compare(v[ub - 1], v[lb]) < 0) {
      ArrayUtils.swap(v, ub - 1, lb);
    }
    if (order.compare(v[ub], v[ub - 1]) < 0) {
      ArrayUtils.swap(v, ub - 1, ub);
    }
  }

  private void fourSort(T[] v, int one, int four) {
    int two = one + 1;
    int three = four - 1;
    twoSort(v, one, two);
    twoSort(v, three, four);
    twoSort(v, one, three);
    twoSort(v, two, four);
    twoSort(v, two, three);
  }
} // class Quicksorter
