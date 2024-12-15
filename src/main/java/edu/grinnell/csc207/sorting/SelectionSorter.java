package edu.grinnell.csc207.sorting;

import java.util.Comparator;

import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class SelectionSorter<T> implements Sorter<T> {
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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
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
    for (int i = 0; i < values.length; i++) {
      int min = indexOfSmallest(i, values.length, values);
      ArrayUtils.swap(values, i, min);
    } // for
  } // sort(T[])

  /**
   * Returns the index of the smallest value between 
   * start and end of an array.
   * 
   * @param start
   *    The start index (inclusive).
   * @param end
   *    The end index (exclusive).
   * @param values
   *    The array.
   * @return
   *    The index of the minimum value.
   */
  public int indexOfSmallest(int start, int end, T[] values) {
    int min = start;
    for (int n = start; n < end; n++) {
      if (order.compare(values[min], values[n]) > 0) {
        min = n;
      } // if
    } // for
    return min;
  } // indexOfSmallest(int, int, T[])
} // class SelectionSorter
